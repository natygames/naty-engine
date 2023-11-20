package com.nativegame.nattyengine.entity.particle.modifier;

import com.nativegame.nattyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface ParticleModifier {

    void updateParticle(Particle particle, long elapsedMillis);

}
