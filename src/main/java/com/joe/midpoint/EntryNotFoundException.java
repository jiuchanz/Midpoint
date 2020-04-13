package com.joe.midpoint;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException(Integer id) {
        super("Device Id not found: " + id);
    }
}
