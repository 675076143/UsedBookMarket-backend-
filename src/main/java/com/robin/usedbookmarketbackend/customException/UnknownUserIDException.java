package com.robin.usedbookmarketbackend.customException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnknownUserIDException extends Exception {
    private static final long serialVersionUID=1L;

    public UnknownUserIDException(String message){
        super(message);
    }
}
