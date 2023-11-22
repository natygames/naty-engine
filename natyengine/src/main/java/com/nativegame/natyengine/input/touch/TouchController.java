package com.nativegame.natyengine.input.touch;

import android.view.View;

import com.nativegame.natyengine.input.InputController;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface TouchController extends InputController, View.OnTouchListener {

    float getTouchX();

    float getTouchY();

    boolean isTouchDown();

    List<TouchEvent> getTouchEvents();

}
