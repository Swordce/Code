package com.cxmedia.goods.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.VersionInfo;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.camera.CameraHelper;
import com.cxmedia.goods.camera.CameraListener;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.common.EventMsg;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.ui.user.activity.BindPhoneActivity;
import com.cxmedia.goods.ui.user.activity.CodeVerificationActivity;
import com.cxmedia.goods.ui.user.activity.EditPasswordActivity;
import com.cxmedia.goods.ui.user.activity.PhoneCodeVerificationActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.GlideApp;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.AnimationView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FacePreviewActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {

    @BindView(R.id.iv_face)
    ImageView ivFace;
    //    @BindView(R.id.face_camera)
//    CameraView faceCamera;
    @BindView(R.id.texture_preview)
    TextureView previewView;
    @BindView(R.id.tv_change_type)
    TextView tvChangeType;
    @BindView(R.id.iv_anim_face)
    AnimationView animFace;

    public static final String TYPE = "type";//0-->预览，1-->登录,2---开通
    private BottomSheetDialog typeDialog;
    private BottomSheetDialog pwdDialog;
    private BottomSheetDialog refundDialog;
    private String refundMoney;
    private String orderNo;
    private String mchtNo;
    private String empNo;
    private String smsCode;
    private String registrationId;
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;

    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private FaceEngine faceEngine;
    private int afCode = -1;
    private Camera.Size previewSize;
    private boolean isDefectFace = false;
    private AnimationDrawable anim;
    private int type;
    private CameraHelper cameraHelper;
    private OrderPresenter orderPresenter;

    private void setWindowBrightness(int brightness) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness / 255.0f;
        window.setAttributes(lp);
    }

    @Override
    public void initView() {
        setWindowBrightness(255);
        Intent intent = getIntent();
        type = intent.getIntExtra("faceType", Contents.OPEN_FACE_LOGIN);
        smsCode = intent.getStringExtra("smsCode");
        refundMoney = intent.getStringExtra("ordAmt");
        orderNo = intent.getStringExtra("orderNo");
        registrationId = intent.getStringExtra("registrationId");
        mchtNo = (String) Cache.get("mchtNo");
        empNo = (String) Cache.get("empNo");
        if (type == Contents.OPEN_FACE_LOGIN) {
            ivFace.setVisibility(View.VISIBLE);
            animFace.setVisibility(View.GONE);
            GlideApp.with(this).asDrawable().load(R.drawable.ic_face_white).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFace);
            tvChangeType.setVisibility(View.GONE);
        } else if (type == Contents.START_FACE_REFUND) {
            ivFace.setVisibility(View.GONE);
            animFace.setVisibility(View.VISIBLE);
            animFace.setIsRepeat(true);
            animFace.setData(getAnimationData());
            animFace.start();
            tvChangeType.setText("切换验证方式");
        }else if(type == Contents.START_FACE_LOGIN){
            ivFace.setVisibility(View.VISIBLE);
            animFace.setVisibility(View.GONE);
            GlideApp.with(this).asDrawable().load(R.drawable.ic_face_white).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFace);
            tvChangeType.setText("平视手机，正对光源");
        } else {
            ivFace.setVisibility(View.VISIBLE);
            animFace.setVisibility(View.GONE);
            GlideApp.with(this).asDrawable().load(R.drawable.ic_face_white).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFace);
            tvChangeType.setVisibility(View.GONE);
        }
        previewView.getViewTreeObserver().addOnGlobalLayoutListener(this);


        if (cameraHelper != null) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.CAMERA, Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.READ_PHONE_STATE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            try {
                                cameraHelper.start();
                            } catch (Exception e) {
                                Toast.makeText(FacePreviewActivity.this, "启动相机失败 " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            Toast.makeText(FacePreviewActivity.this, "请设置相机权限", Toast.LENGTH_SHORT).show();
                            AppManager.getAppManager().finishActivity();
                        }
                    }).start();
        }

    }


    private void checkPermissions() {
        if (AndPermission.hasPermissions(this, NEEDED_PERMISSIONS)) {
            initCamera();
            cameraHelper.start();
        } else {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.CAMERA, Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.READ_PHONE_STATE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            initCamera();
                            cameraHelper.start();
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            Toast.makeText(FacePreviewActivity.this, "请设置相机权限", Toast.LENGTH_SHORT).show();
                            AppManager.getAppManager().finishActivity();
                        }
                    }).start();
        }

    }

    private void initCamera() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        CameraListener cameraListener = new CameraListener() {
            @Override
            public void onCameraOpened(Camera camera, int cameraId, int displayOrientation, boolean isMirror) {
                previewSize = camera.getParameters().getPreviewSize();
            }


            @Override
            public void onPreview(final byte[] nv21, final Camera camera) {
                try {
                    if (!isDefectFace) {
                        final List<FaceInfo> faceInfoList = new ArrayList<>();
                        initEngine();
                        int code = faceEngine.detectFaces(nv21, previewSize.width, previewSize.height, FaceEngine.CP_PAF_NV21, faceInfoList);
                        if (code == ErrorInfo.MOK && faceInfoList.size() > 0) {
                            isDefectFace = true;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Camera.Size size = camera.getParameters().getPreviewSize();
                                    YuvImage image = new YuvImage(nv21, ImageFormat.NV21, size.width, size.height, null);
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    image.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, stream);
                                    Bitmap bmp = BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size());
                                    Log.e("fdsaf","44444");
                                    ivFace.setImageBitmap(bmp);

                                    //**********************
                                    //因为图片会放生旋转，因此要对图片进行旋转到和手机在一个方向上
                                    Bitmap bitmap = rotateMyBitmap(bmp);
                                    Log.e("fdsaf","1111");
                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                                    if(type == Contents.OPEN_FACE_LOGIN) {
                                        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                                .addFormDataPart("faceScope", "1")
                                                .addFormDataPart("empNo", empNo)
                                                .addFormDataPart("verificationCode", smsCode)
                                                .addFormDataPart("file", "file", RequestBody.create(MediaType.parse("image*//*"), stream1.toByteArray()))
                                                .build();
                                        orderPresenter.doSaveFace(requestBody);
                                    }else if(type == Contents.START_FACE_REFUND) {
                                        CalendarUtil util = new CalendarUtil();
                                        Log.e("fdsaf","222");
                                        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                                .addFormDataPart("memberId", mchtNo)
                                                .addFormDataPart("orgTermOrdId", orderNo)
                                                .addFormDataPart("ordAmt", refundMoney)
                                                .addFormDataPart("transDate", util.getTime())
                                                .addFormDataPart("file", "file", RequestBody.create(MediaType.parse("image*//*"), stream1.toByteArray()))
                                                .build();
                                        orderPresenter.doOrderFaceRefund(requestBody);
                                    }else if(type == Contents.OPEN_FACE_REFUND) {
                                        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                                .addFormDataPart("faceScope", "2")
                                                .addFormDataPart("empNo", empNo)
                                                .addFormDataPart("verificationCode", smsCode)
                                                .addFormDataPart("file", "file", RequestBody.create(MediaType.parse("image*//*"), stream1.toByteArray()))
                                                .build();
                                        orderPresenter.doSaveFace(requestBody);
                                    }else if(type == Contents.START_FACE_LOGIN) {
                                        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                                .addFormDataPart("registrationId", registrationId)
                                                .addFormDataPart("file", "file", RequestBody.create(MediaType.parse("image*//*"), stream1.toByteArray()))
                                                .build();
                                        orderPresenter.doFaceLogin(requestBody);
                                    }
                                }
                            });
                        } else {
                            isDefectFace =false;
                            Log.e(getClass().getSimpleName(), "检测人脸失败,错误码" + code);
                        }

                    }

                } catch (Exception e) {
                    isDefectFace =false;
                    Toast.makeText(FacePreviewActivity.this, "SDK加载失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCameraClosed() {
                Log.i(TAG, "onCameraClosed: ");
            }

            @Override
            public void onCameraError(Exception e) {
                Log.i(TAG, "onCameraError: " + e.getMessage());
                Toast.makeText(FacePreviewActivity.this, "相机异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCameraConfigurationChanged(int cameraID, int displayOrientation) {
                Log.i(TAG, "onCameraConfigurationChanged: " + cameraID + "  " + displayOrientation);
            }
        };

        cameraHelper = new CameraHelper.Builder()
                .previewViewSize(new Point(previewView.getMeasuredWidth(), previewView.getMeasuredHeight()))
                .rotation(getWindowManager().getDefaultDisplay().getRotation())
                .specificCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
                .isMirror(false)
                .previewOn(previewView)
                .cameraListener(cameraListener)
                .build();
        cameraHelper.init();
    }


    public Bitmap rotateMyBitmap(Bitmap bmp) {
        //*****旋转一下
        Matrix matrix = new Matrix();
        matrix.postRotate(-90);

        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);

        Bitmap nbmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        return nbmp2;
    }


    private ArrayList<AnimationView.AnimData> getAnimationData() {
        ArrayList<AnimationView.AnimData> datas = new ArrayList<>();
        AnimationView.AnimData data = new AnimationView.AnimData();
        data.filePath = getResources().getIdentifier("ic_face_left", "drawable", getPackageName());
        datas.add(data);
        AnimationView.AnimData data1 = new AnimationView.AnimData();
        data1.filePath = getResources().getIdentifier("ic_face_right", "drawable", getPackageName());
        datas.add(data1);
        AnimationView.AnimData data2 = new AnimationView.AnimData();
        data2.filePath = getResources().getIdentifier("ic_face_all", "drawable", getPackageName());
        datas.add(data2);
        return datas;
    }

    @Override
    protected void onPause() {
        if (animFace.getVisibility() == View.VISIBLE) {
            animFace.pause();
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        if (animFace.getVisibility() == View.VISIBLE) {
            animFace.resume();
        }
        super.onResume();
    }

    @Override
    public void getData() {
    }

    @Override
    public int getLayout() {
        return R.layout.activity_preview_face_2;
    }


    @OnClick({R.id.iv_close, R.id.tv_change_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_change_type:
                if (type != Contents.START_FACE_REFUND) {
                    typeDialog = new BottomSheetDialog(this);
                    View typeView = getLayoutInflater().inflate(R.layout.layout_type, null);
                    TextView tvCommonPwd = typeView.findViewById(R.id.tv_common_pwd);
                    TextView tvPhoneCode = typeView.findViewById(R.id.tv_phone_code);
                    TextView tvCancel = typeView.findViewById(R.id.tv_cancel);
                    tvCancel.setOnClickListener(this);
                    tvPhoneCode.setOnClickListener(this);
                    tvCommonPwd.setOnClickListener(this);
                    typeDialog.setContentView(typeView);
                    typeDialog.show();
                } else {
                    pwdDialog = new BottomSheetDialog(this);
                    View refundView = getLayoutInflater().inflate(R.layout.layout_refund_pwd, null);
                    TextView tvChangePwd = refundView.findViewById(R.id.tv_change_common_pwd);
                    tvChangePwd.setOnClickListener(this);
                    pwdDialog.setContentView(refundView);
                    pwdDialog.show();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_common_pwd:
                if (pwdDialog != null && pwdDialog.isShowing()) {
                    pwdDialog.dismiss();
                }
                EventBus.getDefault().postSticky(new EventMsg(0));
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_forget_pay_pwd:
                if (pwdDialog != null && pwdDialog.isShowing()) {
                    pwdDialog.dismiss();
                }
                Intent intent2 = new Intent(this, PhoneCodeVerificationActivity.class);
                startActivity(intent2);
                refundDialog.dismiss();
                break;
            case R.id.tv_common_pwd:
                Intent intent = new Intent(this, CodeVerificationActivity.class);
                startActivity(intent);
                typeDialog.dismiss();
                break;
            case R.id.tv_phone_code:
                Intent intent1 = new Intent(this, PhoneCodeVerificationActivity.class);
                startActivity(intent1);
                typeDialog.dismiss();
                break;
            case R.id.tv_cancel:
                if (typeDialog != null && typeDialog.isShowing()) {
                    typeDialog.dismiss();
                }
                break;
        }
    }


    private void initEngine() {
        try {
            if (faceEngine == null) {
                faceEngine = new FaceEngine();
                faceEngine.active(this, Contents.FACE_APP_ID, Contents.FACE_APP_KEY);
                afCode = faceEngine.init(this, FaceEngine.ASF_DETECT_MODE_VIDEO, FaceEngine.ASF_OP_270_ONLY,
                        16, 1, FaceEngine.ASF_FACE_RECOGNITION | FaceEngine.ASF_FACE_DETECT | FaceEngine.ASF_LIVENESS);
                VersionInfo versionInfo = new VersionInfo();
                faceEngine.getVersion(versionInfo);
            }
        } catch (Exception e) {
            Toast.makeText(FacePreviewActivity.this, "SDK初始化失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACTION_REQUEST_PERMISSIONS) {
            boolean isAllGranted = true;
            for (int grantResult : grantResults) {
                isAllGranted &= (grantResult == PackageManager.PERMISSION_GRANTED);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (faceEngine != null) {
            faceEngine.unInit();
        }
        if (cameraHelper != null) {
            cameraHelper.stop();
        }
        super.onDestroy();
    }

    @Override
    public void onGlobalLayout() {
        previewView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//        initCamera();
        checkPermissions();
    }

    @Override
    public void orderInfoError(String errorMsg) {
        isDefectFace = false;
        ToastUtils.showShortToast(this,errorMsg);
    }

    @Override
    public void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result) {

    }

    @Override
    public void orderInfoListResult(List<OrderInfoListResult.RowsBean> result) {

    }

    @Override
    public void OrderSettleResult(OrderCategroyResult result) {

    }

    @Override
    public void orderDetailResult(OrderDetailResult.DataBean result) {

    }

    @Override
    public void getSmsCodeResult(String result) {

    }

    @Override
    public void saveFaceSuccessResult(String result) {
        if (anim != null) {
            anim.stop();
        }
        ToastUtils.showShortToast(this,result);
        if(type != Contents.START_FACE_REFUND) {
            EventBus.getDefault().postSticky(new EventMsg(type));
            AppManager.getAppManager().finishActivity(BindPhoneActivity.class);
        }
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void faceLoginResult(LoginResult result) {
        if(result.getIsFirst() != 0) {
            Intent intent = new Intent(this, EditPasswordActivity.class);
            startActivity(intent);
        }else {
            ToastUtils.showShortToast(this,"登录成功");
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void resetRefundPasswordResult(CommonResult result) {

    }

    @Override
    public void faceRefundResult(String result) {
        ToastUtils.showShortToast(this,result);
    }

    @Override
    public void passwordRefundResult(String result) {

    }

    @Override
    public void setPresenter(OrderPresenter presenter) {
        if (presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }
}
