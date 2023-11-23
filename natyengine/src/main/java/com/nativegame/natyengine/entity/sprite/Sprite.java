package com.nativegame.natyengine.entity.sprite;

import android.graphics.Canvas;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.RectangleShapeEntity;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Sprite extends RectangleShapeEntity {

    protected Texture mTexture;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Sprite(Engine engine, Texture texture) {
        this(engine, 0, 0, texture);
    }

    public Sprite(Engine engine, float x, float y, Texture texture) {
        super(engine, x, y, texture.getWidth(), texture.getHeight());
        mTexture = texture;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Texture getTexture() {
        return mTexture;
    }

    public void setTexture(Texture texture) {
        mTexture = texture;
        setWidth(mTexture.getWidth());
        setHeight(mTexture.getHeight());
        resetScalePivot();
        resetRotationPivot();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onDrawCanvas(Canvas canvas) {
        canvas.drawBitmap(mTexture.getBitmap(), 0, 0, mPaint);
    }
    //========================================================

}
