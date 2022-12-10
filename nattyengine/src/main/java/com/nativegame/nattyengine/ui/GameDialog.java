package com.nativegame.nattyengine.ui;

import static android.view.View.OnTouchListener;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class GameDialog implements OnTouchListener, Animation.AnimationListener {

    protected final GameActivity mParent;
    private ViewGroup mRootLayout;
    private View mRootView;
    private int mRootLayoutId;

    private int mEnterAnimationId;
    private int mExitAnimationId;

    private boolean mIsShowing = false;
    private boolean mIsHiding = true;

    protected GameDialog(GameActivity activity) {
        mParent = activity;
    }

    //--------------------------------------------------------
    // Methods to init the dialog
    //--------------------------------------------------------
    protected void setContentView(int layoutId) {
        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        mRootView = LayoutInflater.from(mParent).inflate(layoutId, activityRoot, false);
    }

    protected void setRootLayoutId(int rootLayoutId) {
        mRootLayoutId = rootLayoutId;
    }

    protected void setEnterAnimationId(int enterAnimationId) {
        mEnterAnimationId = enterAnimationId;
    }

    protected void setExitAnimationId(int exitAnimationId) {
        mExitAnimationId = exitAnimationId;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods to change state of dialog
    // show, dismiss and hide
    //--------------------------------------------------------
    public final void show() {
        if (mIsShowing) {
            return;
        }
        mIsShowing = true;
        mIsHiding = false;

        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        mRootLayout = (ViewGroup) LayoutInflater.from(mParent).inflate(mRootLayoutId, activityRoot, false);
        activityRoot.addView(mRootLayout);
        mRootLayout.setOnTouchListener(this);
        mRootLayout.addView(mRootView);
        startShowAnimation();
        onShow();
    }

    public final void dismiss() {
        if (!mIsShowing) {
            return;
        }
        if (mIsHiding) {
            return;
        }
        mIsHiding = true;
        mParent.dismissDialog();
        startHideAnimation();
        onDismiss();
    }

    protected void onShow() {
    }

    protected void onDismiss() {
    }

    protected void onHide() {
    }

    public boolean isShowing() {
        return mIsShowing;
    }
    //========================================================

    private void startShowAnimation() {
        if (mEnterAnimationId == 0) {
            return;
        }
        Animation enterAnimation = AnimationUtils.loadAnimation(mParent, mEnterAnimationId);
        mRootView.startAnimation(enterAnimation);
    }

    private void startHideAnimation() {
        if (mExitAnimationId == 0) {
            hideViews();
            return;
        }
        Animation exitAnimation = AnimationUtils.loadAnimation(mParent, mExitAnimationId);
        exitAnimation.setAnimationListener(this);
        mRootView.startAnimation(exitAnimation);
    }

    private void hideViews() {
        mIsShowing = false;
        mRootLayout.removeView(mRootView);
        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        activityRoot.removeView(mRootLayout);
        onHide();
    }

    protected View findViewById(int id) {
        // Important to not use this method before layout created
        return mRootView.findViewById(id);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // We ignore touch events outside the dialog
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        hideViews();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

}
