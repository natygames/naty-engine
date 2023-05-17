package com.nativegame.nattyengine.audio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseAudioManager<T extends Audio> implements AudioManager<T> {

    protected final Context mContext;

    private boolean mIsAudioEnable = true;

    private final List<T> mAudios = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseAudioManager(Context context) {
        mContext = context;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void play() {
        int size = mAudios.size();
        for (int i = 0; i < size; i++) {
            Audio audio = mAudios.get(i);
            audio.play();
        }
    }

    @Override
    public void stop() {
        int size = mAudios.size();
        for (int i = 0; i < size; i++) {
            Audio audio = mAudios.get(i);
            audio.stop();
        }
    }

    @Override
    public void pause() {
        int size = mAudios.size();
        for (int i = 0; i < size; i++) {
            Audio audio = mAudios.get(i);
            audio.pause();
        }
    }

    @Override
    public void resume() {
        int size = mAudios.size();
        for (int i = 0; i < size; i++) {
            Audio audio = mAudios.get(i);
            audio.resume();
        }
    }

    @Override
    public void release() {
        int size = mAudios.size();
        for (int i = size - 1; i >= 0; i--) {
            Audio audio = mAudios.get(i);
            audio.release();
        }
    }

    @Override
    public void addAudio(T audio) {
        mAudios.add(audio);
    }

    @Override
    public void removeAudio(T audio) {
        mAudios.remove(audio);
    }

    @Override
    public boolean isAudioEnable() {
        return mIsAudioEnable;
    }

    @Override
    public void setAudioEnable(boolean audioEnable) {
        if (audioEnable) {
            mIsAudioEnable = true;
            onAudioEnable();
        } else {
            onAudioDisable();
            mIsAudioEnable = false;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onAudioEnable();

    protected abstract void onAudioDisable();
    //========================================================

}
