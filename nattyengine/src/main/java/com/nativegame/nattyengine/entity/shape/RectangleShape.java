package com.nativegame.nattyengine.entity.shape;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.engine.collision.Collidable;
import com.nativegame.nattyengine.engine.collision.CollisionType;
import com.nativegame.nattyengine.engine.collision.hitbox.HitBox;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class RectangleShape extends Shape implements Collidable {

    private HitBox mHitBox;
    private CollisionType mCollisionType = CollisionType.NONE;

    protected int mWidth;
    protected int mHeight;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected RectangleShape(Engine engine, float x, float y, int width, int height) {
        super(engine, x, y);
        mWidth = width;
        mHeight = height;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public float getCenterX() {
        return mX + mWidth / 2f;
    }

    public void setCenterX(float x) {
        mX = x - mWidth / 2f;
    }

    public float getCenterY() {
        return mY + mHeight / 2f;
    }

    public void setCenterY(float y) {
        mY = y - mHeight / 2f;
    }

    public float getEndX() {
        return mX + mWidth;
    }

    public void setEndX(float x) {
        mX = x - mWidth;
    }

    public float getEndY() {
        return mY + mHeight;
    }

    public void setEndY(float y) {
        mY = y - mHeight;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public HitBox getCollisionHitBox() {
        return mHitBox;
    }

    @Override
    public void setCollisionHitBox(HitBox hitBox) {
        mHitBox = hitBox;
    }

    @Override
    public CollisionType getCollisionType() {
        return mCollisionType;
    }

    @Override
    public void setCollisionType(CollisionType type) {
        mCollisionType = type;
    }

    @Override
    public void onCollision(Collidable collidable) {
    }

    @Override
    public void onPostUpdate(long elapsedMillis) {
        if (mCollisionType != CollisionType.NONE) {
            // Update collision bound position
            mHitBox.setCollisionBoundsPosition((int) getCenterX(), (int) getCenterY());
        }
    }

    @Override
    public boolean isCulling(Camera camera) {
        return camera.getWorldToScreenX(mX) > camera.getScreenWidth()
                || camera.getWorldToScreenY(mY) > camera.getScreenHeight()
                || camera.getWorldToScreenX(mX + mWidth) < 0
                || camera.getWorldToScreenY(mY + mHeight) < 0;
    }

    @Override
    public void reset() {
        super.reset();
        mCollisionType = CollisionType.NONE;
        if (mHitBox != null) {
            mHitBox.getCollisionBitmap().recycle();
        }
        mHitBox = null;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (mHitBox != null) {
            mHitBox.getCollisionBitmap().recycle();
        }
    }
    //========================================================

}
