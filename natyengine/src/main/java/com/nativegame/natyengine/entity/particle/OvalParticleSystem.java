package com.nativegame.natyengine.entity.particle;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.GenericParticle;
import com.nativegame.natyengine.entity.particle.GenericParticleSystem;
import com.nativegame.natyengine.entity.particle.Particle;
import com.nativegame.natyengine.entity.shape.primitive.Oval;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class OvalParticleSystem extends GenericParticleSystem {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public OvalParticleSystem(Engine engine, int width, int height, int minCount) {
        this(engine, width, height, minCount, minCount);
    }

    public OvalParticleSystem(Engine engine, int width, int height, int minCount, int maxCount) {
        super(engine, new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Particle>() {
            @Override
            public Particle createObject() {
                return new GenericParticle<>(engine, new Oval(engine, width, height));
            }
        }, minCount, maxCount));
    }
    //========================================================

}
