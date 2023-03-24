package com.nativegame.nattyengine.entity.sprite;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.texture.TextureGroup;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class TileSprite extends Sprite {

    protected TextureGroup<? extends Texture> mTextureGroup;
    protected int mCurrentIndex;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public TileSprite(Engine engine, TextureGroup<? extends Texture> textureGroup) {
        this(engine, 0, 0, textureGroup, 0);
    }

    public TileSprite(Engine engine, TextureGroup<? extends Texture> textureGroup, int startIndex) {
        this(engine, 0, 0, textureGroup, startIndex);
    }

    public TileSprite(Engine engine, float x, float y, TextureGroup<? extends Texture> textureGroup) {
        this(engine, x, y, textureGroup, 0);
    }

    public TileSprite(Engine engine, float x, float y, TextureGroup<? extends Texture> textureGroup, int startIndex) {
        super(engine, x, y, textureGroup.getTextures().get(startIndex));
        mTextureGroup = textureGroup;
        mCurrentIndex = startIndex;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public TextureGroup<? extends Texture> getTextureGroup() {
        return mTextureGroup;
    }

    public void setTextureGroup(TextureGroup<? extends Texture> textureGroup) {
        mTextureGroup = textureGroup;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onPreDraw(Canvas canvas, Camera camera) {
        mTexture = mTextureGroup.getTextures().get(mCurrentIndex);
    }

    @Override
    public void reset() {
        super.reset();
        mTexture = mTextureGroup.getTextures().get(mCurrentIndex);
    }
    //========================================================

}
