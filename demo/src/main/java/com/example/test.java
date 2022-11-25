package com.example;
import java.util.regex.*;

public class test {
    public static void main(String[] args){
        String content = "axbxcxdxexfx";
   
        String pattern = "((x.*?){2})x";
   
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
     }
    // ((x.*?){k-1})x
}

