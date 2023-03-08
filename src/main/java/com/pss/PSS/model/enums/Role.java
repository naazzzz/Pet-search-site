package com.pss.PSS.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("Это пользователь"),ADMIN("Это админ");

    private final String info;

     Role(String info){
        this.info=info;
    }
}
