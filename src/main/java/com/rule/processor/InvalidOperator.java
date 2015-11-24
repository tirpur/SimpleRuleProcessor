package com.rule.processor;

/**
 * Created by shri on 21/11/15.
 */
public class InvalidOperator extends Exception {
    private String message = null;
    public InvalidOperator() {
        super();
    }
    public InvalidOperator(String msg) {
        message = msg;
    }
    public InvalidOperator(Throwable cause) {
        super(cause);
    }
    public InvalidOperator(String msg, Throwable cause) {
        super(msg,cause);
    }
    @Override
    public String getMessage() {return message;}

    @Override
    public String toString() {
        return message;
    }
}