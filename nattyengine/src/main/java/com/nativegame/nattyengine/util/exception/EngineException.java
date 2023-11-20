package com.nativegame.nattyengine.util.exception;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class EngineException extends Exception {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public EngineException() {
        super();
    }

    public EngineException(String message) {
        super(message);
    }

    public EngineException(Throwable cause) {
        super(cause);
    }

    public EngineException(String message, Throwable cause) {
        super(message, cause);
    }
    //========================================================

}
