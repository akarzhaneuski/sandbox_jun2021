package com.exadel.sandbox.team5.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ResultPage<T> {
    private List<T> content;
    private long totalElements;
}
