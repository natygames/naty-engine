package com.nativegame.nattyengine.entity.sprite;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.input.touch.BoundTouchEventListener;
import com.nativegame.nattyengine.input.touch.TouchEvent;
import com.nativegame.nattyengine.input.touch.TouchEventListener;
import com.nativegame.nattyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ButtonSprite extends Sprite implements BoundTouchEventListener, TouchEventListener {

    private ButtonListener mListener;
    private ButtonState mState = ButtonState.ENABLE;

    public enum ButtonState {
        ENABLE,
        DISABLE,
        PRESSED
    }

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ButtonSprite(Engine engine, Texture texture) {
        super(engine, texture);
    }

    public ButtonSprite(Engine engine, float x, float y, Texture texture) {
        super(engine, x, y, texture);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public ButtonListener getButtonListener() {
        return mListener;
    }

    public void setButtonListener(ButtonListener listener) {
        mListener = listener;
    }

    public ButtonState getButtonState() {
        return mState;
    }

    public void setButtonState(ButtonState state) {
        mState = state;
        onUpdateButtonState();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onAreaTouchEvent(int type, float relativeTouchX, float relativeTouchY) {
        if (mState == ButtonState.DISABLE) {
            return;
        }
        if (type == TouchEvent.TOUCH_DOWN) {
            mState = ButtonState.PRESSED;
            onUpdateButtonState();
        }
    }

    @Override
    public void onTouchEvent(int type, float touchX, float touchY) {
        if (mState == ButtonState.DISABLE) {
            return;
        }
        // Check the touch up event when pressed state to prevent
        // pressing the button in bounds and release somewhere else
        if (type == TouchEvent.TOUCH_UP && mState == ButtonState.PRESSED) {
            mState = ButtonState.ENABLE;
            onUpdateButtonState();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void onUpdateButtonState() {
        if (mListener == null) {
            return;
        }
        switch (mState) {
            case ENABLE:
                mListener.onEnableButton(this);
                break;
            case DISABLE:
                mListener.onDisableButton(this);
                break;
            case PRESSED:
                mListener.onPressedButton(this);
                break;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface ButtonListener {

        void onEnableButton(ButtonSprite button);

        void onDisableButton(ButtonSprite button);

        void onPressedButton(ButtonSprite button);

    }
    //========================================================

}
