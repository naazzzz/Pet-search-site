package com.pss.PSS.model.enums;

public enum Status {
    ACTIVE("Это активное объявление"),INACTIVE("Это неактивное объявление");

    private final String info;

    Status(String info){
        this.info=info;
    }
}
