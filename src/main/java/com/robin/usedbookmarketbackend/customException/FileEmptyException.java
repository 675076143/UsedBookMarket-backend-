package com.robin.usedbookmarketbackend.customException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileEmptyException extends Exception {

    private static final long serialVersionUID=1L;

    public FileEmptyException(String message){
        super(message);
    }
}
