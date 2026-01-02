package org.quickbase.customexceptions;

public class DriverNotInvokedException extends RuntimeException {
    public DriverNotInvokedException(String message) {
        super(message);
    }
}
