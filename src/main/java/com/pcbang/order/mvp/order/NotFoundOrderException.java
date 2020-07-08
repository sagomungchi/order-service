package com.pcbang.order.mvp.order;

public class NotFoundOrderException extends RuntimeException {
    public NotFoundOrderException(){
        super();
    }

    public NotFoundOrderException(String message){
        super(message);
    }
}
