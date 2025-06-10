package com.bag.Book_Store.util;

import lombok.Getter;

@Getter
public enum Role {
    USER(1L, "Usuario","USER"),
    ADMIN(2L, "Administrador","ADMIN");

    private final Long id;
    private final String name;
    private final String authorityName;
    Role(Long id, String name, String authorityName) {
        this.id = id;
        this.name = name;
        this.authorityName = authorityName;
    }
}
