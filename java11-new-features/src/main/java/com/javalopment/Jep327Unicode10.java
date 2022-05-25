package com.javalopment;

/**
 * https://unicode-table.com/en/sets/emoji/
 */
public class Jep327Unicode10 {

    public static void main(String[] args) {
        String codepoint = "U+1F603";   // smiling face with open mouth
        System.out.println(codePoints(codepoint));
    }

    // UTF-16
    static char[] codePoints(String codePoint) {
        Integer i = Integer.valueOf(codePoint.substring(2), 16);
        return Character.toChars(i);
    }
}
