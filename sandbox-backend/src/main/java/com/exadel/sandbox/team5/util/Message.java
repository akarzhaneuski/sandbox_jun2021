package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
public class Message {
    private String text;
    private String subject;

    public Message(String text, @Nullable String subject) {
        this.text = text;
        this.subject = subject;
    }
}
