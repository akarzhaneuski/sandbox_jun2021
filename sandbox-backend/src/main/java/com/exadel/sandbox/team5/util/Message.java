package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
public class Message {
    private String message;
    private String subject;

    public Message(String message, @Nullable String subject) {
        this.message = message;
        this.subject = subject;
    }
}
