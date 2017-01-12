package com.hujie.recyclerviewpra;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

/**
 * Created by hujie on 2017/1/11.
 */

public class MyFloatingBehavior<V extends View> extends FloatingActionButton.Behavior {
    private boolean isShow=false;

    public MyFloatingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes==ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed>0 && !isShow){

            animaOut(child);
            isShow=true;

            if (listener!=null){
                listener.onStateChange(false);
            }
        }else if (dyConsumed<0 && isShow){

            animaIn(child);
            isShow=false;

            if (listener!=null){
                listener.onStateChange(true);
            }
        }
    }

    private void animaOut(FloatingActionButton button){
        ViewCompat.animate(button).translationY(button.getHeight()+
                200).setDuration(300)
                .start();
    }

    private void animaIn(FloatingActionButton button){
        button.setVisibility(View.VISIBLE);
        ViewCompat.animate(button).translationY(0).setDuration(300)
                .withLayer().setListener(null).start();
    }


    public static <V extends View> MyFloatingBehavior<V> from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof MyFloatingBehavior)) {
            throw new IllegalArgumentException(
                    "The view is not associated with BottomSheetBehavior");
        }
        return (MyFloatingBehavior<V>) behavior;
    }

    /**
     * 接口回调
     * 回调参数isShow把FloatingActionBar的显示隐藏状态传递出去
     */

    private OnStateChangeListener listener;

    public void setOnStateChangeListener(OnStateChangeListener listener){
        this.listener=listener;
    }

    public interface OnStateChangeListener{
        void onStateChange(boolean isShow);
    }
}
