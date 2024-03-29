package com.nativegame.natyengine.entity.particle;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.GenericParticle;
import com.nativegame.natyengine.entity.particle.GenericParticleSystem;
import com.nativegame.natyengine.entity.particle.Particle;
import com.nativegame.natyengine.entity.shape.primitive.Circle;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

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
