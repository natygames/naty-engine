package com.nativegame.nattyengine.texture;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseTextureManager<T extends Texture, S extends TextureGroup<T>> implements TextureManager<T, S> {

    protected final Context mContext;

    private final List<T> mTextures = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseTextureManager(Context context) {
        mContext = context;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void release() {
        int size = mTextures.size();
        for (int i = size - 1; i >= 0; i--) {
            Texture t = mTextures.get(i);
            t.release();
        }
    }

    @Override
    public void addTexture(T texture) {
        mTextures.add(texture);
    }

    @Override
    public void removeTexture(T texture) {
        mTextures.remove(texture);
    }
    //========================================================

}
