package com.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class countMatchedTimes {
    public static int countMatchedTimes(String INPUT, String REGEX){
        // REGEX = "(((?<!\\\\)\").*?((?<!\\\\)\"))";
        // REGEX = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*//.*";
        // INPUT = "\"have // in line\\\"\"na\"nb\"; //nc";
        REGEX = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*/\\*.*";
        INPUT = "/*";
        System.out.println(REGEX);
        System.out.println(INPUT);
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;
     
        while(m.find()) {
            count++;
            System.out.println("Match number "+count);
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
        }
        return count;
    }
    public static void main(String[] args){
        int num = countMatchedTimes.countMatchedTimes(null, null);
        System.out.println(num);
    }
}
