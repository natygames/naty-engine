package com.nativegame.nattyengine.texture;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface TextureGroup<T extends Texture> {

    List<T> getTextures();

    int getTextureCount();

    void addTexture(T texture);

    void removeTexture(T texture);

    void addTextures(List<T> textures);

    void removeTextures(List<T> textures);

    void release();

}
