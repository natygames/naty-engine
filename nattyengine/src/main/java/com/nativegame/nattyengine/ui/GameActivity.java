package com.nativegame.nattyengine.ui;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nativegame.nattyengine.level.LevelManager;
import com.nativegame.nattyengine.sound.SoundManager;

import java.util.Stack;

public class GameActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "content";

    private final Stack<GameDialog> mDialogStack = new Stack<>();

    private LevelManager mLevelManager;
    private SoundManager mSoundManager;

    private int mContainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    protected void setContainerId(int containerId) {
        mContainerId = containerId;
    }

    protected void setLevelManager(LevelManager levelManager) {
        mLevelManager = levelManager;
    }

    protected void setSoundManager(SoundManager soundManager) {
        mSoundManager = soundManager;
    }

    public LevelManager getLevelManager() {
        return mLevelManager;
    }

    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    public void navigateToFragment(GameFragment gameFragment) {
        navigateToFragment(gameFragment, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void navigateToFragment(GameFragment gameFragment, int enterAnimationId, int exitAnimationId) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnimationId, exitAnimationId, enterAnimationId, exitAnimationId)
                .replace(mContainerId, gameFragment, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void navigateBack() {
        // Back to previous fragment
        getSupportFragmentManager().popBackStack();
    }

    public void showDialog(GameDialog newDialog) {
        // Push new dialog into stack
        mDialogStack.push(newDialog);
        newDialog.show();
    }

    public void dismissDialog() {
        // Pop the last dialog in stack
        mDialogStack.pop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSoundManager != null) {
            mSoundManager.pauseMusic();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSoundManager != null) {
            mSoundManager.resumeMusic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSoundManager != null) {
            mSoundManager.unloadMusic();
            mSoundManager.unloadSound();
        }
    }

    @Override
    public void onBackPressed() {
        if (!mDialogStack.empty()) {
            // Dismiss the last dialog in stack
            mDialogStack.peek().dismiss();
            return;
        }
        final GameFragment fragment = (GameFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
