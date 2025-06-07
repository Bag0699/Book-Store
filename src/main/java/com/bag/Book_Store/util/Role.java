package com.bag.Book_Store.util;

import lombok.Getter;

@Getter
public enum Role {
    USER(1L, "Usuario"),
    ADMIN(2L, "Administrador");

    private final Long id;
    private final String name;
    Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
