package com.exadel.sandbox.team5.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Pair {
    private String first;
    private String second;

    public Pair(long first, double second){
        this.first=Long.toString(first);
        this.second=Double.toString(second);
    }

    public Pair(String first, long second){
        this.first=first;
        this.second=Long.toString(second);
    }

    public Pair(int first, long second) {
        this.first = "" + first;
        this.second = "" + second;
    }
}
