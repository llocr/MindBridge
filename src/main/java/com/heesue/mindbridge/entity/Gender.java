package com.heesue.mindbridge.entity;

public enum Gender {
    M("남자"), F("여자"), N("선택없음");

    private final String value;

    private Gender(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
