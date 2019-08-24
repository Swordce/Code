package com.cxmedia.goods.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
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
import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.camera.CameraHelper;
import com.cxmedia.goods.camera.CameraListener;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.common.EventMsg;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.user.activity.CodeVerificationActivity;
import com.cxmedia.goods.ui.user.activity.PhoneCodeVerificationActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.GlideApp;
import com.cxmedia.goods.widgets.AnimationView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FacePreviewActivity extends BaseActivity implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {

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
    private boolean isRefund = false;
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
        type = intent.getIntExtra(TYPE, 0);
        isRefund = intent.getBooleanExtra("refund", false);
        if (type == 0) {
            ivFace.setVisibility(View.VISIBLE);
            animFace.setVisibility(View.GONE);
            GlideApp.with(this).asDrawable().load(R.drawable.ic_face_white).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFace);
            tvChangeType.setText("切换验证方式");
        } else if (type == 1) {
            ivFace.setVisibility(View.GONE);
            animFace.setVisibility(View.VISIBLE);
            animFace.setIsRepeat(true);
            animFace.setData(getAnimationData());
            animFace.start();
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
                    .permission(Permission.CAMERA,Permission.ACCESS_FINE_LOCATION,Permission.ACCESS_COARSE_LOCATION, Permission.READ_PHONE_STATE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            try {
                                cameraHelper.start();
                            }catch (Exception e) {
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

//        try {
//            faceCamera.setCameraDataCallback(new CameraView.CameraDataCallback() {
//                @Override·
//                public void onYuvDataFrame(byte[] nv21, Camera camera) {
//
//                }
//
//                @Override
//                public void onH264DataFrame(byte[] h264, int width, int height) {
//
//                }
//
//                @Override
//                public void onAacDataFrame(byte[] aac, int length) {
//
//                }
//
//                @Override
//                public void onNv21DataFrame(final byte[] nv21, Camera camera) {
//                    try {
//                        if (!isDefectFace) {
//                            final List<FaceInfo> faceInfoList = new ArrayList<>();
//                            previewSize = camera.getParameters().getPreviewSize();
//                            initEngine();
//                            int code = faceEngine.detectFaces(nv21, previewSize.width, previewSize.height, FaceEngine.CP_PAF_NV21, faceInfoList);
//                            if (code == ErrorInfo.MOK && faceInfoList.size() > 0) {
//                                isDefectFace = true;
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if (type == 1) {
//                                            if (anim != null) {
//                                                anim.stop();
//                                            }
//                                            Intent intent = new Intent(FacePreviewActivity.this, MainActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                            startActivity(intent);
//                                            AppManager.getAppManager().finishActivity();
//                                        } else {
//                                            Toast.makeText(FacePreviewActivity.this, "检测到人脸", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                });
//                            }
//
//                        }
//
//                    } catch (Exception e) {
//                        Toast.makeText(FacePreviewActivity.this, "SDK加载失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
//        } catch (Exception e) {
//            Toast.makeText(FacePreviewActivity.this, "出现异常 " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

    }



    private void checkPermissions() {
        if(AndPermission.hasPermissions(this, NEEDED_PERMISSIONS)) {
            initCamera();
            cameraHelper.start();
        } else {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.CAMERA,Permission.ACCESS_FINE_LOCATION,Permission.ACCESS_COARSE_LOCATION, Permission.READ_PHONE_STATE)
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
            public void onPreview(byte[] nv21, Camera camera) {
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
                                    Toast.makeText(FacePreviewActivity.this, "检测到人脸", Toast.LENGTH_SHORT).show();

                                    if (type == 1) {
                                        if (anim != null) {
                                            anim.stop();
                                        }
                                        Intent intent = new Intent(FacePreviewActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        AppManager.getAppManager().finishActivity();
                                    } else {
                                        Toast.makeText(FacePreviewActivity.this, "检测到人脸", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }else {
                            Log.e(getClass().getSimpleName(),"检测人脸失败,错误码" + code);
                        }

                    }

                } catch (Exception e) {
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
                Toast.makeText(FacePreviewActivity.this, "相机异常",Toast.LENGTH_SHORT).show();
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
                if (!isRefund) {
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
        if(cameraHelper != null) {
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
}
