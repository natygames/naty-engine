package com.nativegame.natyengine.texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseTextureGroup<T extends Texture> implements TextureGroup<T> {

    private final List<T> mTextures = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseTextureGroup() {
    }

    protected BaseTextureGroup(List<T> textures) {
        mTextures.addAll(textures);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public List<T> getTextures() {
        return mTextures;
    }

    @Override
    public int getTextureCount() {
        return mTextures.size();
    }

    @Override
    public void addTexture(T texture) {
        mTextures.add(texture);
    }

    @Override
    public void removeTexture(T texture) {
        mTextures.remove(texture);
    }

    @Override
    public void addTextures(List<T> textures) {
        mTextures.addAll(textures);
    }

    @Override
    public void removeTextures(List<T> textures) {
        mTextures.removeAll(textures);
    }

    @Override
    public void release() {
        int size = mTextures.size();
        for (int i = 0; i < size; i++) {
            Texture t = mTextures.get(i);
            t.release();
        }
        mTextures.clear();
    }
    //========================================================

}
