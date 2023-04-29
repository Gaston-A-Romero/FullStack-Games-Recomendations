package com.Reviews.Exceptions;

public class NotYourPropertyException extends RuntimeException{
    public NotYourPropertyException(String msg){
        super(msg);
    }
}
