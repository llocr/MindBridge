package com.heesue.mindbridge.entity;

public enum ApprovalStatus {
    PENDING("검토 중"),
    ACCEPTED("수락"),
    REJECTED("거절");

    private final String description;

    ApprovalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
