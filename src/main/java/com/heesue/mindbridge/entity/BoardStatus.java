package com.heesue.mindbridge.entity;

public enum BoardStatus {
    PUBLISHED("게시"),
    DELETED("삭제"),
    UNDER_REVIEW("검토"),
    HIDDEN("숨김"),
    ARCHIVED("보관");

    private final String value;

    BoardStatus(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}