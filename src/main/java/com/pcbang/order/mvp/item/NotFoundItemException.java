package com.pcbang.order.mvp.item;

public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(){
        super();
    }

    public NotFoundItemException(String message){
        super(message);
    }
}
