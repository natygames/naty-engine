package com.nativegame.natyengine.entity.particle;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpriteParticleSystem extends GenericParticleSystem {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpriteParticleSystem(Engine engine, Texture texture, int minCount) {
        this(engine, texture, minCount, minCount);
    }

    public SpriteParticleSystem(Engine engine, Texture texture, int minCount, int maxCount) {
        super(engine, new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Particle>() {
            @Override
            public Particle createObject() {
                return new GenericParticle<>(engine, new Sprite(engine, texture));
            }
        }, minCount, maxCount));
    }
    //========================================================

}
