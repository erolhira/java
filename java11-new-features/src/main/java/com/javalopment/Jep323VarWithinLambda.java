package com.javalopment;

import java.util.List;

public class Jep323VarWithinLambda {

    public static void main(String[] args) {

        var list = List.of("a", "b", "c");
        list.forEach((var s) -> System.out.println(s));
    }
}
