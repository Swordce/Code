package com.cxmedia.goods.widgets;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.cxmedia.goods.R;
import com.cxmedia.goods.utils.DensityUtils;

public class SelectButton extends LinearLayout {
    Context context;
    private View select_button;
    private LinearLayout select_bg;
    boolean isOffline = false;//绿色为不选中状态

    public SelectButton(Context context) {
        super(context);
    }

    public SelectButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.item_lout_select_button, this);
        select_button = view.findViewById(R.id.select_button);
        select_bg = (LinearLayout) view.findViewById(R.id.select_bg);
    }

    private void initData() {
        //1.根据选中状态,初始化按钮
        initView();
        //按钮的点击事件
        select_bg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isOffline = !isOffline;
                onButtonClickListener.onClick(isOffline);
                //2.根据点击事件,加载动画
                initAnimotion(isOffline);
            }
        });
    }

    private void initView() {
        //如果选中切换颜色,不选中用处理
        if (!isOffline) {
            //1.处理颜色
            GradientDrawable background = (GradientDrawable) select_bg.getBackground();
            background.setColor(context.getResources().getColor(R.color.color_a1a1a1));
            background.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.color_a1a1a1));

            GradientDrawable butontDrawable = (GradientDrawable) select_button.getBackground();
            butontDrawable.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.color_a1a1a1));
            //2.处理位置
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator translation = ObjectAnimator.ofFloat(select_button, "translationX", 0f, DensityUtils.dp2px(context, 0));
            animatorSet.playTogether(translation);//动画同时执行
            animatorSet.setDuration(0);
            animatorSet.start();
        }
    }

    /**
     * 处理动画
     */
    private void initAnimotion(Boolean click) {
        //选中
        if (click) {
            //1.由灰色变为蓝色
            GradientDrawable background = (GradientDrawable) select_bg.getBackground();
            background.setColor(context.getResources().getColor(R.color.theme_color));
            background.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.theme_color));

            GradientDrawable butontDrawable = (GradientDrawable) select_button.getBackground();
            butontDrawable.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.theme_color));
            //2.动画按钮由左边移动到右边

        } else {
            //1.由蓝色变为灰色
            GradientDrawable background = (GradientDrawable) select_bg.getBackground();
            background.setColor(context.getResources().getColor(R.color.color_a1a1a1));
            background.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.color_a1a1a1));

            GradientDrawable butontDrawable = (GradientDrawable) select_button.getBackground();
            butontDrawable.setStroke(DensityUtils.dp2px(context, 2), context.getResources().getColor(R.color.color_a1a1a1));
            //2.动画按钮由右边移动到左边
        }
        createTranslateAnimation(click);
    }


    //这里做一个接口回调
    OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        public void onClick(boolean click);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 创建位移动画
     */
    private void createTranslateAnimation(boolean click) {
        //(int fromXType, float fromXValue, int toXType, float toXValue,int fromYType, float fromYValue, int toYType, float toYValue)
        //由灰色变为绿色,由左边滑到右边
        if (click == true) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator translation = ObjectAnimator.ofFloat(select_button, "translationX", 0f, DensityUtils.dp2px(context, 36));
            animatorSet.playTogether(translation);//动画同时执行
            animatorSet.setDuration(100);
            animatorSet.start();
        } else {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator translation = ObjectAnimator.ofFloat(select_button, "translationX", DensityUtils.dp2px(context, 37), 0);
            animatorSet.playTogether(translation);//动画同时执行
            animatorSet.setDuration(100);
            animatorSet.start();
        }
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
        initData();
    }

}