package com.Reviews.Exceptions;

public class ContentNotFoundException extends NullPointerException{
    public ContentNotFoundException(String msg){
        super(msg);
    }
}
