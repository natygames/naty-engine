package com.nativegame.natyengine.util.exception;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class EngineRuntimeException extends RuntimeException {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public EngineRuntimeException() {
        super();
    }

    public EngineRuntimeException(String message) {
        super(message);
    }

    public EngineRuntimeException(Throwable cause) {
        super(cause);
    }

    public EngineRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    //========================================================

}
