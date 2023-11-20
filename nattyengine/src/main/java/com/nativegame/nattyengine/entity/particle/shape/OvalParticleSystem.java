package com.nativegame.nattyengine.entity.particle.shape;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.particle.GenericParticle;
import com.nativegame.nattyengine.entity.particle.GenericParticleSystem;
import com.nativegame.nattyengine.entity.particle.Particle;
import com.nativegame.nattyengine.entity.shape.geometry.Oval;
import com.nativegame.nattyengine.util.pool.Pool;
import com.nativegame.nattyengine.util.pool.SafeFixedObjectPool;

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
