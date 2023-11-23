package com.nativegame.natyengine.entity.shape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface RectangleShape extends Shape {

    public int getWidth();

    public void setWidth(int width);

    public int getHeight();

    public void setHeight(int height);

    public float getCenterX();

    public void setCenterX(float x);

    public float getCenterY();

    public void setCenterY(float y);

    public float getEndX();

    public void setEndX(float x);

    public float getEndY();

    public void setEndY(float y);

}
