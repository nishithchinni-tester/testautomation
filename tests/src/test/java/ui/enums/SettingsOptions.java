package ui.enums;

import lombok.Getter;

@Getter
public enum SettingsOptions {
    TABLES("Tables"),
    STRUCTURE("Structure"),
    ROLES("Roles"),
    AUTOMATIONS("Automations"),
    APPMANAGEMENT("App management"),
    FIELDS("Fields");

    private final String value;
    SettingsOptions(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return this.value;
    }
}
