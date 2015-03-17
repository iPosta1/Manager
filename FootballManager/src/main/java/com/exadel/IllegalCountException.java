package com.exadel;

/**
 * @author Nike
 *
 */
public class IllegalCountException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public IllegalCountException(String message){
       super(message);
    }

  }