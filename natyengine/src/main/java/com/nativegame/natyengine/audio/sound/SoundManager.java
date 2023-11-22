package com.nativegame.natyengine.audio.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.nativegame.natyengine.audio.BaseAudioManager;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SoundManager extends BaseAudioManager<Sound> {

    private static final int MAX_STREAMS = 10;

    private SoundPool mSoundPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SoundManager(Context context) {
        super(context);
        // Init SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(MAX_STREAMS)
                .build();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Sound load(int soundId) {
        int id = mSoundPool.load(mContext, soundId, 1);
        Sound sound = new Sound(this, mSoundPool, id);
        addAudio(sound);
        return sound;
    }

    @Override
    public void release() {
        super.release();
        mSoundPool.release();
        mSoundPool = null;
    }

    @Override
    protected void onAudioEnable() {
    }

    @Override
    protected void onAudioDisable() {
        stop();
    }
    //========================================================

}
