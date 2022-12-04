package com.example.examprojectspringboot.enums;

public enum StudyFormat {
    ONLINE("online study format"), OFFLINE("offline study format");
    private final String displayValue;

    StudyFormat(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
