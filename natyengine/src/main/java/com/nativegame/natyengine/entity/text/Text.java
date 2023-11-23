package com.nativegame.natyengine.entity.text;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.RectangleShapeEntity;
import com.nativegame.natyengine.util.exception.EngineRuntimeException;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Text extends RectangleShapeEntity {

    private String mText;
    private TextHorizontalAlign mHorizontalAlign = TextHorizontalAlign.CENTER;
    private TextVerticalAlign mVerticalAlign = TextVerticalAlign.CENTER;

    private final Rect mTextBound = new Rect(-1, -1, -1, -1);

    public enum TextHorizontalAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum TextVerticalAlign {
        TOP,
        CENTER,
        BOTTOM
    }

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Text(Engine engine, int width, int height) {
        this(engine, 0, 0, width, height, "");
    }

    public Text(Engine engine, float x, float y, int width, int height) {
        this(engine, x, y, width, height, "");
    }

    public Text(Engine engine, int width, int height, String text) {
        this(engine, 0, 0, width, height, text);
    }

    public Text(Engine engine, float x, float y, int width, int height, String text) {
        super(engine, x, y, width, height);
        mText = text;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public TextHorizontalAlign getTextHorizontalAlign() {
        return mHorizontalAlign;
    }

    public void setTextHorizontalAlign(TextHorizontalAlign horizontalAlign) {
        mHorizontalAlign = horizontalAlign;
    }

    public TextVerticalAlign getTextVerticalAlign() {
        return mVerticalAlign;
    }

    public void setTextVerticalAlign(TextVerticalAlign verticalAlign) {
        mVerticalAlign = verticalAlign;
    }

    public float getTextSize() {
        return mPaint.getTextSize();
    }

    public void setTextSize(float textSize) {
        mPaint.setTextSize(textSize);
    }

    public Typeface getTextTypeface() {
        return mPaint.getTypeface();
    }

    public void setTextTypeface(Typeface typeface) {
        mPaint.setTypeface(typeface);
    }

    public float getTextX() {
        switch (mHorizontalAlign) {
            case LEFT:
                return 0;
            case CENTER:
                return getWidth() / 2f - getTextWidth() / 2f;
            case RIGHT:
                return getWidth() - getTextWidth();
            default:
                throw new EngineRuntimeException("HorizontalAlign not found!");
        }
    }

    public float getTextY() {
        switch (mVerticalAlign) {
            case TOP:
                return 0;
            case CENTER:
                return getHeight() / 2f - getTextHeight() / 2f;
            case BOTTOM:
                return getHeight() - getTextHeight();
            default:
                throw new EngineRuntimeException("VerticalAlign not found!");
        }
    }

    public int getTextWidth() {
        String[] texts = mText.split("\n");
        // Find the line with the largest width
        int maxTextWidth = 0;
        for (String text : texts) {
            mPaint.getTextBounds(text, 0, text.length(), mTextBound);
            if (mTextBound.width() > maxTextWidth) {
                maxTextWidth = mTextBound.width();
            }
        }
        return maxTextWidth;
    }

    public int getTextHeight() {
        String[] texts = mText.split("\n");
        int lineCount = texts.length;
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        return lineCount * mTextBound.height();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onDrawCanvas(Canvas canvas) {
        // Check if text has next line sign and divide it
        String[] texts = mText.split("\n");
        int textWidth = getTextWidth();
        int textHeight = getTextHeight();
        int textLineCount = texts.length;
        int textLineHeight = textHeight / textLineCount;
        float textX = getTextX();
        float textY = getTextY() + textLineHeight;   // Align the baseline

        // Draw the text to canvas
        for (int i = 0; i < textLineCount; i++) {
            canvas.drawText(texts[i], textX, textY, mPaint);
            // Move to the next line position y
            textY += textLineHeight;
        }

        // Print debug info
        if (mEngine.isDebugMode() && mEngine.getDebugger().isDebugText()) {
            // Draw the bounding rectangle to canvas
            float boundX = getTextX();
            float boundY = getTextY();
            canvas.drawRect(boundX, boundY, boundX + textWidth, boundY + textHeight,
                    mEngine.getDebugger().getDebugPaint());
            canvas.drawRect(0, 0, getWidth(), getHeight(), mEngine.getDebugger().getDebugPaint());
        }
    }
    //========================================================

}
