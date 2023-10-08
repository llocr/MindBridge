package com.heesue.mindbridge.entity;

public enum Role {
    ROLE_CLIENT("내담자"), ROLE_COUNSELOR("상담자"), ROLE_ADMIN("관리자");

    private final String value;

    private Role(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }

    public static Role fromValue(String value) {
        for(Role role : values()) {
            if(role.getValue().equals(value)) {
                return role;
            }
        }
        return null;
    }
}
