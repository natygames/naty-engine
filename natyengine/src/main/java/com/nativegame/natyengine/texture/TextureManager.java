package com.nativegame.natyengine.texture;

import android.graphics.Bitmap;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface TextureManager<T extends Texture, S extends TextureGroup<T>> {

    T loadTexture(int drawableId);

    T loadTexture(Bitmap bitmap);

    S loadTextureGroup(int[] drawableIds);

    S loadTextureGroup(Bitmap[] bitmaps);

    void release();

    void addTexture(T graphic);

    void removeTexture(T graphic);

}
