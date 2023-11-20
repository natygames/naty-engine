package com.nativegame.nattyengine.ui;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nativegame.nattyengine.audio.music.MusicManager;
import com.nativegame.nattyengine.audio.sound.SoundManager;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.texture.texture2d.Texture2DManager;

import java.util.Stack;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "content";

    private Engine mEngine;
    private MusicManager mMusicManager;
    private SoundManager mSoundManager;
    private Texture2DManager mTextureManager;
    private int mFragmentContainerId;

    private final Stack<GameDialog> mDialogStack = new Stack<>();

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Engine getEngine() {
        return mEngine;
    }

    public MusicManager getMusicManager() {
        return mMusicManager;
    }

    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    public Texture2DManager getTextureManager() {
        return mTextureManager;
    }

    public void setFragmentContainer(int fragmentContainerId) {
        mFragmentContainerId = fragmentContainerId;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mEngine = new Engine();
        mMusicManager = new MusicManager(this);
        mSoundManager = new SoundManager(this);
        mTextureManager = new Texture2DManager(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMusicManager.pause();
        mSoundManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMusicManager.resume();
        mSoundManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMusicManager.release();
        mSoundManager.release();
        mTextureManager.release();
    }

    @Override
    public void onBackPressed() {
        if (!mDialogStack.empty()) {
            // Dismiss the last dialog in stack
            mDialogStack.peek().dismiss();
            return;
        }
        GameFragment fragment = (GameFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void navigateToFragment(GameFragment fragment) {
        navigateToFragment(fragment, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void navigateToFragment(GameFragment fragment, int enterAnimationId, int exitAnimationId) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnimationId, exitAnimationId, enterAnimationId, exitAnimationId)
                .replace(mFragmentContainerId, fragment, FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }

    public void navigateBack() {
        // Back to previous fragment
        getSupportFragmentManager().popBackStack();
    }

    public void showDialog(GameDialog dialog) {
        // Push new dialog into stack
        mDialogStack.push(dialog);
        dialog.show();
    }

    public void dismissDialog() {
        // Pop the last dialog in stack
        mDialogStack.pop();
    }
    //========================================================

}
