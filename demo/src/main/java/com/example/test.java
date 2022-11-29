package com.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class test
{
    private static final String REGEX = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*//.*";
    // private static final String INPUT =
    //                                 "***************";
 
    public static void main( String[] args ){
       Pattern p = Pattern.compile(REGEX);
       Matcher m = p.matcher("/**//**//**//**/"); // 获取 matcher 对象
       int count = 0;
 
       while(m.find()) {
         count++;
         System.out.println("Match number "+count);
         System.out.println("start(): "+m.start());
         System.out.println("end(): "+m.end());
      }
   }
}