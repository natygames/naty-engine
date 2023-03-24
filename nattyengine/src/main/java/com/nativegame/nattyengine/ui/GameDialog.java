package com.nativegame.nattyengine.ui;

import static android.view.View.OnTouchListener;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameDialog implements OnTouchListener, Animation.AnimationListener {

    protected final GameActivity mParent;

    private ViewGroup mContainerView;
    private View mContentView;
    private int mContainerViewId;
    private int mEnterAnimationId;
    private int mExitAnimationId;

    private boolean mIsShowing = false;
    private boolean mIsHiding = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GameDialog(GameActivity activity) {
        mParent = activity;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isShowing() {
        return mIsShowing;
    }

    protected View getContentView() {
        return mContentView;
    }

    protected void setContentView(int layoutId) {
        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        mContentView = LayoutInflater.from(mParent).inflate(layoutId, activityRoot, false);
    }

    protected void setContainerView(int containerViewId) {
        mContainerViewId = containerViewId;
    }

    protected void setEnterAnimationId(int enterAnimationId) {
        mEnterAnimationId = enterAnimationId;
    }

    protected void setExitAnimationId(int exitAnimationId) {
        mExitAnimationId = exitAnimationId;
    }

    protected View findViewById(int id) {
        // Important to not use this method before layout created
        return mContentView.findViewById(id);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // We ignore touch event outside the dialog
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        hide();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void show() {
        if (mIsShowing) {
            return;
        }
        mIsShowing = true;
        mIsHiding = false;

        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        mContainerView = (ViewGroup) LayoutInflater.from(mParent).inflate(mContainerViewId, activityRoot, false);
        activityRoot.addView(mContainerView);
        mContainerView.setOnTouchListener(this);
        mContainerView.addView(mContentView);
        startEnterAnimation();
        onShow();
    }

    public void dismiss() {
        if (!mIsShowing || mIsHiding) {
            return;
        }
        mIsHiding = true;
        mParent.dismissDialog();
        startExitAnimation();
        onDismiss();
    }

    private void hide() {
        mIsShowing = false;
        mContainerView.removeView(mContentView);
        ViewGroup activityRoot = (ViewGroup) mParent.findViewById(android.R.id.content);
        activityRoot.removeView(mContainerView);
        onHide();
    }

    protected void onShow() {
    }

    protected void onDismiss() {
    }

    protected void onHide() {
    }

    private void startEnterAnimation() {
        if (mEnterAnimationId == 0) {
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(mParent, mEnterAnimationId);
        mContentView.startAnimation(animation);
    }

    private void startExitAnimation() {
        if (mExitAnimationId == 0) {
            hide();
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(mParent, mExitAnimationId);
        animation.setAnimationListener(this);
        mContentView.startAnimation(animation);
    }
    //========================================================

}
