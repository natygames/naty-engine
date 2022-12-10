package com.nativegame.nattyengine.entity;

import android.graphics.Canvas;

import com.nativegame.nattyengine.Game;

public abstract class UIGameObject extends GameObject implements Drawable {

    private boolean mUIHaveChanged = false;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            onDrawUI();
        }
    };

    protected UIGameObject(Game game) {
        super(game);
    }

    protected abstract void onDrawUI();

    @Override
    public void draw(Canvas canvas) {
        mGame.getGameActivity().runOnUiThread(mRunnable);
        mUIHaveChanged = false;
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return mUIHaveChanged;
    }

    protected void drawUI() {
        mUIHaveChanged = true;
    }

}
