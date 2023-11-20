package com.nativegame.nattyengine.entity.particle;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.sprite.Sprite;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.util.pool.Pool;
import com.nativegame.nattyengine.util.pool.SafeFixedObjectPool;

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
