package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class FadeOutModifier extends AlphaModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public FadeOutModifier(long duration) {
        super(255, 0, duration);
    }

    public FadeOutModifier(long duration, long startDelay) {
        super(255, 0, duration, startDelay);
    }

    public FadeOutModifier(long duration, Tweener tweener) {
        super(255, 0, duration, tweener);
    }

    public FadeOutModifier(long duration, long startDelay, Tweener tweener) {
        super(255, 0, duration, startDelay, tweener);
    }
    //========================================================

}
