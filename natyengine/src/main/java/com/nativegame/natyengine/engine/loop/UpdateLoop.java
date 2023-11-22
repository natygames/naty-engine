package com.nativegame.natyengine.engine.loop;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class UpdateLoop extends BaseLoop {

    private UpdateListener mListener;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public UpdateLoop() {
        super();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public UpdateListener getListener() {
        return mListener;
    }

    public void setListener(UpdateListener listener) {
        mListener = listener;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdateLoop(long elapsedMillis) {
        mListener.update(elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface UpdateListener {

        void update(long elapsedMillis);

    }
    //========================================================

}
