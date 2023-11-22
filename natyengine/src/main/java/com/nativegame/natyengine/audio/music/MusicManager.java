package com.nativegame.natyengine.audio.music;

import android.content.Context;
import android.media.MediaPlayer;

import com.nativegame.natyengine.audio.BaseAudioManager;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class MusicManager extends BaseAudioManager<Music> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public MusicManager(Context context) {
        super(context);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Music load(int musicId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(mContext, musicId);
        Music music = new Music(this, mediaPlayer);
        addAudio(music);
        return music;
    }

    @Override
    protected void onAudioEnable() {
        play();
    }

    @Override
    protected void onAudioDisable() {
        stop();
    }
    //========================================================

}
