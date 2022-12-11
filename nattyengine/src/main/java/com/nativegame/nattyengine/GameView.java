package com.nativegame.nattyengine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.nativegame.nattyengine.entity.Drawable;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameView extends View {

    private List<Drawable> mDrawables;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawables(List<Drawable> drawables) {
        mDrawables = drawables;
    }

    public void draw() {
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (mDrawables) {
            int min = getMinLayer();
            int max = getMaxLayer();
            for (int i = min; i <= max; i++) {
                drawLayer(canvas, i);
            }
        }
    }

    private void drawLayer(Canvas canvas, int layer) {
        int size = mDrawables.size();
        for (int i = 0; i < size; i++) {
            Drawable d = mDrawables.get(i);
            if (d.isVisible() && d.getLayer() == layer) {
                d.draw(canvas);
            }
        }
    }

    private int getMinLayer() {
        int min = mDrawables.get(0).getLayer();
        // Find min layer
        int size = mDrawables.size();
        for (int i = 0; i < size; i++) {
            int layer = mDrawables.get(i).getLayer();
            if (layer < min) {
                min = layer;
            }
        }
        return min;
    }

    private int getMaxLayer() {
        int max = mDrawables.get(0).getLayer();
        // Find max layer
        int size = mDrawables.size();
        for (int i = 0; i < size; i++) {
            int layer = mDrawables.get(i).getLayer();
            if (layer > max) {
                max = layer;
            }
        }
        return max;
    }

}
