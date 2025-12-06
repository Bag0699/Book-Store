package com.bag.Book_Store.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    AUTHOR_NOT_FOUND("ERR_ATR_001", "Author not found"),
    INVALID_AUTHOR("ERR_ATR_002", "Author parameters is invalid"),

    BOOK_NOT_FOUND("ERR_BK_001", "Book not found"),
    INVALID_BOOK("ERR_BK_002", "Book parameters is invalid"),

    CATEGORY_NOT_FOUND("ERR_CAT_001", "Category not found"),
    INVALID_CATEGORY("ERR_CAT_002", "Category parameters is invalid"),

    EDITORIAL_NOT_FOUND("ERR_EDT_001", "Editorial not found"),
    INVALID_EDITORIAL("ERR_EDT_002", "Editorial parameters is invalid"),

    FORMAT_NOT_FOUND("ERR_FRM_001", "Format not found"),
    INVALID_FORMAT("ERR_FRM_002", "Format parameters is invalid"),

    ORDER_NOT_FOUND("ERR_ORD_001", "Order not found"),
    INVALID_ORDER("ERR_ORD_002", "Order parameters is invalid"),

    ORDER_ITEM_NOT_FOUND("ERR_ITM_001", "Order item not found"),
    INVALID_ORDER_ITEM("ERR_ITM_002", "Order item parameters is invalid"),

    STORE_NOT_FOUND("ERR_STR_001", "Store not found"),
    INVALID_STORE("ERR_STR_002", "Store parameters is invalid"),

    USER_NOT_FOUND("ERR_USR_001", "User not found"),
    INVALID_USER("ERR_USR_002", "User parameters is invalid"),

    GENERIC_ERROR("ERR_GEN_001", "Un error inesperado ocurrió.");

    private final String code;
    private final String message;

    ErrorCatalog(String name, String message) {
        this.code = name;
        this.message = message;
    }
}
