package com.nativegame.nattyengine.entity.particle;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.sprite.Sprite;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.util.pool.Pool;
import com.nativegame.nattyengine.util.pool.SafeFixedObjectPool;

public class SpriteParticleSystem extends GenericParticleSystem {

    public SpriteParticleSystem(Engine engine, Texture texture, int min, int max) {
        super(engine, new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Particle>() {
            @Override
            public Particle createObject() {
                return new GenericParticle<>(engine, new Sprite(engine, texture));
            }
        }, min, max));
    }

}
