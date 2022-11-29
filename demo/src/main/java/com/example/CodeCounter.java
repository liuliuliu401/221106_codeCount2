package com.example;
import java.io.*;
import java.util.regex.*;
import java.util.*;

public class CodeCounter{
    private int commentCount = 0;
    private int blankCount = 0;
    private int codeCount = 0;
    private int codePlusCommentCount = 0;
    private int currentLineNumber = 0;

    private static final String regexOfDoubleSlash = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*//.*";
    private Pattern patternOfDoubleSlash = Pattern.compile(regexOfDoubleSlash);
    private static final String regexOfSlashStar = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*/\\*.*";
    private Pattern patternOfSlashStar = Pattern.compile(regexOfSlashStar);
    private static final String regexOfStarSlash = "^.*\\*/([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*";
    private Pattern patternOfStarSlash = Pattern.compile(regexOfStarSlash);
    private static final String regexnOfSimpleStarSlash = "\\*/";
    private Pattern patternOfSimpleStarSlash = Pattern.compile(regexnOfSimpleStarSlash);
    private Matcher m;
    
    // they're defined here and used2reseted in reading a line, but serving as global variable.
    private int flagOfCommenting = 0;
    private int havePreviousCode = 0;

    private void reset(){
        this.commentCount = 0;
        this.blankCount = 0;
        this.codeCount = 0;
        this.codePlusCommentCount = 0;
        this.currentLineNumber = 0;
        this.havePreviousCode = 0;
        this.flagOfCommenting = 0;
    }

    private void addLine(){
        this.currentLineNumber ++ ;
        System.out.println(this.currentLineNumber);
    }

    private void addCode(){
        this.codeCount ++ ;
        System.out.println("Code");
    }

    private void addComment(){
        this.commentCount ++ ;
        System.out.println("Comment");
    }
    
    private void addCodePlusComment(){
        this.codePlusCommentCount ++ ;
        System.out.println("Code-Comment");
    }

    private void addBlank(){
        this.blankCount++ ;
        System.out.println("Blank");
    }

    private String readMyLine(BufferedReader br){
        try{
            String s = br.readLine();
            if (s != null)
                addLine();
                System.out.println(s);
            return s;
        }catch(IOException e) {
            return null;
        }
    }

    private int[] getStatistics(){
        System.out.println("Code statistics information:");
        System.out.format("total count = %d\n", this.blankCount + this.commentCount
        + this.codeCount + this.codePlusCommentCount);
        System.out.format("blank count = %d\n", this.blankCount);        
        System.out.format("pure code count = %d\n", this.codeCount);
        System.out.format("comment count = %d\n", this.commentCount);

        System.out.format("code with comment = %d\n", this.codePlusCommentCount);
        int[] result = {this.blankCount, this.codeCount, this.commentCount, this.codePlusCommentCount};
        return result;
    }

    public static int[] readAndCount(String fileName){
        File file=new File(fileName); /*test0*/
        int[] result = CodeCounter.countComment(file);
        // Arrays.stream(result).forEach(System.out::println);
        return result;
    }

    public static void main(String [] args){
        CodeCounter.readAndCount(args[0]);
        // CodeCounter.readAndCount("demo\\test_code\\equivalence_class_test\\test12.java");
    }

    public static int[] countComment(File filename){
        CodeCounter counter = new CodeCounter();
        int[] result = {};
        String line = "";
        counter.reset();
        // System.out.println("This line is a test for // and /* in \"\"");
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            counter.decideType(counter, line, br);
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            result = counter.getStatistics();
        }
        return result;
    }

    private void countCommentingofLine(CodeCounter counter, String line){
        if (line.length() == 0){
            if (flagOfCommenting == 1)
                counter.addComment();
            else
                counter.addBlank();
        }
        else if (line.startsWith("//")){
            counter.addComment();
        }
        else if (patternOfDoubleSlash.matcher(line).find() && ! patternOfSlashStar.matcher(line).find()){
            counter.addCodePlusComment();
        }
        else if (patternOfSlashStar.matcher(line).find()){
            if (line.startsWith("/*")){
                counter.addComment();
            }
            else{
                counter.addCodePlusComment();
            }
            m = patternOfSimpleStarSlash.matcher(line);
            if(!m.find())
                flagOfCommenting = 1;
        }
        else{
            if (flagOfCommenting == 1)
                counter.addCodePlusComment();
            else
                counter.addCode();
        }
            
    }

    private void decideType(CodeCounter counter, String line, BufferedReader br){
        reset();
        while((line = counter.readMyLine(br))!= null){
            line = line.trim();
            // 匹配空行
            if (flagOfCommenting == 0){
                countCommentingofLine(counter, line);
            }else{
                if(patternOfStarSlash.matcher(line).find()){
                    m = patternOfSimpleStarSlash.matcher(line);
                    m.find();
                    countCommentingofLine(counter, line.substring(m.end()));
                    flagOfCommenting = 0;
                }else{
                    counter.addComment();
                }
            }
        }
    }

    public static void walk(File path){
        File[] list = path.listFiles();
        if(list == null)
            return;
        for (File f: list){
            if (f.isDirectory()){
                walk(f);
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else{
                System.out.println("File:" + f.getAbsoluteFile());
                if(f.getName().matches(".*\\.java$")){
                    countComment(f);
                }
            }
        }
    }
}