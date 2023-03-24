package com.nativegame.nattyengine.audio.sound;

import android.media.SoundPool;

import com.nativegame.nattyengine.audio.BaseAudio;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Sound extends BaseAudio {

    private static final float DEFAULT_SOUND_VOLUME = 1.0f;
    private static final long SOUND_TIMEOUT = 50;

    private final SoundPool mSoundPool;

    private int mSoundId;
    private int mStreamId;
    private float mLeftVolume;
    private float mRightVolume;
    private int mLoopCount;
    private float mRate = 1.0f;
    private long mLastPlayTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Sound(SoundManager soundManager, SoundPool soundPool, int soundId) {
        super(soundManager);
        mSoundPool = soundPool;
        mSoundId = soundId;
        setVolume(DEFAULT_SOUND_VOLUME, DEFAULT_SOUND_VOLUME);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public void setVolume(float leftVolume, float rightVolume) {
        mLeftVolume = leftVolume;
        mRightVolume = rightVolume;
        if (mStreamId != 0) {
            mSoundPool.setVolume(mStreamId, leftVolume, rightVolume);
        }
    }

    public int getLoopCount() {
        return mLoopCount;
    }

    public void setLoopCount(int loopCount) {
        mLoopCount = loopCount;
        if (mStreamId != 0) {
            mSoundPool.setLoop(mStreamId, mLoopCount);
        }
    }

    public float getRate() {
        return mRate;
    }

    public void setRate(float rate) {
        mRate = rate;
        if (mStreamId != 0) {
            mSoundPool.setRate(mStreamId, mRate);
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void play() {
        if (!getParent().isAudioEnable()) {
            return;
        }
        // We make sure sound not being called multiple times at once
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastPlayTime > SOUND_TIMEOUT) {
            mLastPlayTime = currentTime;
            mStreamId = mSoundPool.play(mSoundId, mLeftVolume, mRightVolume, 0, mLoopCount, mRate);
        }
    }

    @Override
    public void stop() {
        if (!getParent().isAudioEnable()) {
            return;
        }
        if (mStreamId != 0) {
            mSoundPool.stop(mStreamId);
        }
    }

    @Override
    public void pause() {
        if (!getParent().isAudioEnable()) {
            return;
        }
        if (mStreamId != 0) {
            mSoundPool.pause(mStreamId);
        }
    }

    @Override
    public void resume() {
        if (!getParent().isAudioEnable()) {
            return;
        }
        if (mStreamId != 0) {
            mSoundPool.resume(mStreamId);
        }
    }

    @Override
    public void release() {
        mSoundPool.unload(mSoundId);
        mSoundId = 0;
        mStreamId = 0;
        getParent().removeAudio(this);
    }

    @Override
    protected SoundManager getParent() {
        return (SoundManager) super.getParent();
    }
    //========================================================

}
