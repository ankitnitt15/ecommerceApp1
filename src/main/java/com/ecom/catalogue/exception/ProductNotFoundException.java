package com.ecom.catalogue.exception;

import com.ecom.catalogue.model.Product;

public class ProductNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String msg){
        super(msg);
    }
}
