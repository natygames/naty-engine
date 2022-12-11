package com.nativegame.nattyengine;

import com.nativegame.nattyengine.engine.GameEngine;
import com.nativegame.nattyengine.input.TouchController;
import com.nativegame.nattyengine.level.Level;
import com.nativegame.nattyengine.sound.SoundManager;
import com.nativegame.nattyengine.ui.GameActivity;

import java.util.Random;

/**
 * Created by Oscar Liang on 2022/12/11
 */

/*
 *    MIT License
 *
 *    Copyright (c) 2022 Oscar Liang
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in all
 *    copies or substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *    SOFTWARE.
 */

public class Game {

    private static boolean sDebugMode = false;

    private final GameActivity mActivity;
    private final GameView mGameView;
    private final GameEngine mGameEngine;
    private final int mScreenWidth;
    private final int mScreenHeight;
    private final Random mRandom = new Random();

    private TouchController mTouchController;
    private SoundManager mSoundManager;
    private Level mLevel;
    private float mPixelFactor;

    public Game(GameActivity activity, GameView gameView) {
        mActivity = activity;
        mGameView = gameView;
        mGameEngine = new GameEngine(gameView);
        mScreenWidth = gameView.getWidth();
        mScreenHeight = gameView.getHeight();
    }

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public GameActivity getGameActivity() {
        return mActivity;
    }

    public GameEngine getGameEngine() {
        return mGameEngine;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public Random getRandom() {
        return mRandom;
    }

    public float getPixelFactor() {
        if (mPixelFactor > 0) {
            return mPixelFactor;
        } else {
            throw new IllegalStateException("PixelFactor must be larger than 0!");
        }
    }

    public void setPixelFactor(float basePixel) {
        mPixelFactor = mScreenWidth / basePixel;
    }

    public TouchController getTouchController() {
        if (mTouchController != null) {
            return mTouchController;
        } else {
            throw new IllegalStateException("You need to initialize the TouchController!");
        }
    }

    public void setTouchController(TouchController touchController) {
        mTouchController = touchController;
        mGameView.setOnTouchListener(touchController);
    }

    public SoundManager getSoundManager() {
        if (mSoundManager != null) {
            return mSoundManager;
        } else {
            throw new IllegalStateException("You need to initialize the SoundManager!");
        }
    }

    public void setSoundManager(SoundManager soundManager) {
        mSoundManager = soundManager;
    }

    public Level getLevel() {
        if (mLevel != null) {
            return mLevel;
        } else {
            throw new IllegalStateException("You need to initialize the Level!");
        }
    }

    public void setLevel(Level level) {
        mLevel = level;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods to change state of game
    // start, stop, pause, resume
    //--------------------------------------------------------
    public final void start() {
        mGameEngine.startGame();
        onStart();
    }

    public final void stop() {
        if (mGameEngine.isRunning()) {
            mGameEngine.stopGame();
            onStop();
        }
    }

    public final void pause() {
        if (mGameEngine.isRunning() && !mGameEngine.isPaused()) {
            mGameEngine.pauseGame();
            onPause();
        }
    }

    public final void resume() {
        if (mGameEngine.isRunning() && mGameEngine.isPaused()) {
            mGameEngine.resumeGame();
            onResume();
        }
    }

    protected void onStart() {
    }

    protected void onStop() {
    }

    protected void onPause() {
    }

    protected void onResume() {
    }
    //========================================================

    public static boolean getDebugMode() {
        return sDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        sDebugMode = debugMode;
    }

}
