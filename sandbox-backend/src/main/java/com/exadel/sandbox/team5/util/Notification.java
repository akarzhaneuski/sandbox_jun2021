package com.exadel.sandbox.team5.util;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
public class Notification {
    private String message;
    private String subject;

    public Notification(String message, @Nullable String subject) {
        this.message=message;
        this.subject=subject;
    }
}
