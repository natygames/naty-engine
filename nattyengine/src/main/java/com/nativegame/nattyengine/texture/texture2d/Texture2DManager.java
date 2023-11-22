package com.nativegame.nattyengine.texture.texture2d;

import android.content.Context;
import android.graphics.Bitmap;

import com.nativegame.nattyengine.texture.BaseTextureManager;
import com.nativegame.nattyengine.texture.TextureFormat;
import com.nativegame.nattyengine.util.ResourceUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Texture2DManager extends BaseTextureManager<Texture2D, Texture2DGroup> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Texture2DManager(Context context) {
        super(context);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Texture2D loadTexture(int drawableId) {
        Bitmap bitmap = ResourceUtils.getBitmap(mContext, drawableId);
        return loadTexture(bitmap);
    }

    @Override
    public Texture2D loadTexture(Bitmap bitmap) {
        TextureFormat format;
        switch (bitmap.getConfig()) {
            case RGB_565:
                format = TextureFormat.RGB565;
                break;
            case ARGB_4444:
                format = TextureFormat.ARGB4444;
                break;
            case ARGB_8888:
                format = TextureFormat.ARGB8888;
                break;
            default:
                throw new IllegalArgumentException("Unsupported bitmap format!");
        }
        Texture2D texture = new Texture2D(this, format, bitmap);
        addTexture(texture);

        return texture;
    }

    @Override
    public Texture2DGroup loadTextureGroup(int[] drawableIds) {
        Texture2DGroup group = new Texture2DGroup();
        // Init texture from id and add to group
        int size = drawableIds.length;
        for (int i = 0; i < size; i++) {
            Texture2D t = loadTexture(drawableIds[i]);
            group.addTexture(t);
        }

        return group;
    }

    @Override
    public Texture2DGroup loadTextureGroup(Bitmap[] bitmaps) {
        Texture2DGroup group = new Texture2DGroup();
        // Init texture from bitmap and add to group
        int size = bitmaps.length;
        for (int i = 0; i < size; i++) {
            Texture2D t = loadTexture(bitmaps[i]);
            group.addTexture(t);
        }

        return group;
    }
    //========================================================

}
