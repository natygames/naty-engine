package com.nativegame.nattyengine.entity.particle.shape;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.particle.GenericParticle;
import com.nativegame.nattyengine.entity.particle.GenericParticleSystem;
import com.nativegame.nattyengine.entity.particle.Particle;
import com.nativegame.nattyengine.entity.shape.geometry.Circle;
import com.nativegame.nattyengine.util.pool.Pool;
import com.nativegame.nattyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class CircleParticleSystem extends GenericParticleSystem {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CircleParticleSystem(Engine engine, int radius, int minCount) {
        this(engine, radius, minCount, minCount);
    }

    public CircleParticleSystem(Engine engine, int radius, int minCount, int maxCount) {
        super(engine, new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Particle>() {
            @Override
            public Particle createObject() {
                return new GenericParticle<>(engine, new Circle(engine, radius));
            }
        }, minCount, maxCount));
    }
    //========================================================

}
