package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

public interface ParticleInitializer {

    void initParticle(Particle particle, Random random);

}
