package com.heesue.mindbridge.entity;

public enum ApprovalStatus {
    PENDING("검토 중"),
    ACCEPTED("수락"),
    REJECTED("거절");

    private final String value;

    ApprovalStatus(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
