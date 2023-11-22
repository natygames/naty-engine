package com.nativegame.natyengine.input.touch;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class TouchEvent {

    public static final int TOUCH_DOWN = 0;
    public static final int TOUCH_UP = 1;
    public static final int TOUCH_DRAGGED = 2;

    private int mType;
    private float mTouchX;
    private float mTouchY;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public float getTouchX() {
        return mTouchX;
    }

    public void setTouchX(float touchX) {
        mTouchX = touchX;
    }

    public float getTouchY() {
        return mTouchY;
    }

    public void setTouchY(float touchY) {
        mTouchY = touchY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (mType) {
            case TOUCH_DOWN:
                builder.append("Touch down, ");
                break;
            case TOUCH_UP:
                builder.append("Touch up, ");
                break;
            case TOUCH_DRAGGED:
                builder.append("Touch dragged, ");
                break;
        }
        builder.append(mTouchX);
        builder.append(", ");
        builder.append(mTouchY);
        return builder.toString();
    }
    //========================================================

}
