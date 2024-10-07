package com.nagarro.config;

public enum Constants {

    URL("url"), FAILED("Failed");

    private final Object value;

    Constants(final Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
