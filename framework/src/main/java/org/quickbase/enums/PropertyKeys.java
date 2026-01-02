package org.quickbase.enums;

public enum PropertyKeys {
    ENVIRONMENT("environment"),
    EXECMODE("execMode"),
    PARALLELCOUNT("parallelCount"),
    SUITE("suite"),
    BROWSER("browser"),
    EXECUTIONGROUPS("executionGroups"),
    RPURL("rpEndpoint"),
    RPPROJECT("rpProject"),
    RPLAUNCH("rpLaunch"),
    RPENABLE("rpEnable");
    private final String value;

    PropertyKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
