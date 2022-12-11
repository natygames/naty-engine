package com.nativegame.nattyengine.sound;

import android.media.SoundPool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Sound {

    private static final long MIN_TIMEOUT = 50;

    private final int mSoundPoolId;

    private long mLastPlayTime;

    public Sound(int soundPoolId) {
        mSoundPoolId = soundPoolId;
    }

    public void play(SoundPool soundPool) {
        // We make sure sound not being called multiple times
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastPlayTime > MIN_TIMEOUT) {
            mLastPlayTime = currentTime;
            // Left volume, Right volume, priority (0 = lowest), loop (0 = no), rate (1.0 = normal)
            soundPool.play(mSoundPoolId, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

}
