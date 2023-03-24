package com.nativegame.nattyengine.entity.particles.modifier;

import com.nativegame.nattyengine.entity.particles.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface ParticleModifier {

    void updateParticle(Particle particle, long elapsedMillis);

}
