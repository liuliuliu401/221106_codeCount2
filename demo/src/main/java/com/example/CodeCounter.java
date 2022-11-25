package com.example;
import java.io.*;
import java.util.regex.*;

public class CodeCounter{
    private int commentCount = 0;
    private int blankCount = 0;
    private int codeCount = 0;
    private int codePlusCommentCount = 0;
    private int currentLineNumber = 0;

    private String regexOfDoubleSlash = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*//.*";
    private Pattern patternOfDoubleSlash = Pattern.compile(regexOfDoubleSlash);
    private String regexOfSlashStar = "^([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*/\\*.*";
    private Pattern patternOfSlashStar = Pattern.compile(regexOfSlashStar);
    private String regexOfStarSlash = "^.*\\*/([^\"]|(((?<!\\\\)\").*?((?<!\\\\)\")))*";
    private Pattern patternOfStarSlash = Pattern.compile(regexOfStarSlash);
    private Matcher m;
    

    private int flagOfCommenting = 0;

    private void reset(){
        this.commentCount = 0;
        this.blankCount = 0;
        this.codeCount = 0;
        this.codePlusCommentCount = 0;
        this.currentLineNumber = 0;
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

    private void getStatistics(){
        System.out.println("Code statistics information:\n");
        System.out.format("total count = %d\n", this.blankCount + this.commentCount
        + this.codeCount + this.codePlusCommentCount);
        System.out.format("blank count = %d\n", this.blankCount);
        System.out.format("comment count = %d\n", this.commentCount);
        System.out.format("code count = %d\n", this.codeCount + this.codePlusCommentCount);
        System.out.format("code with comment suffix = %d\n", this.codePlusCommentCount);
    }


    public static void main(String [] args){
        File file=new File(args[0]); /*test0*/
        CodeCounter.countComment(file);/*
            a simple test
        *//*
            a test2
        */System.out.println("/**/");/*
        /*
         * 
         */
        // if (args.length != 1) {
        //     System.err.println("Invalid command line，exactly one argument required");
        //     System.exit(1);
        // }
        // try {
        //     BufferedReader br = new BufferedReader(new FileReader(args[0]));
        //     String strLine;
        //     while ((strLine = br.readLine()) != null) {
        //         //Print the content on the console
        //         System.out.println(strLine);
        //     }
        // } catch(IOException e) {
        //     e.printStackTrace( );
        // }
    }

    public static void countComment(File filename){
        CodeCounter counter = new CodeCounter();
        String line = "";
        counter.reset();
        System.out.println("This line is a test for // and /* in \"\"");
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            counter.  decideType(counter, line, br);
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            counter.getStatistics();
        }

    }

    private void countOutOfCommenting(CodeCounter counter, String line){
        if (line.length() == 0){
            counter.addBlank();
        }
        else if (line.startsWith("//")){
            counter.addComment();
        }
        else if (patternOfDoubleSlash.matcher(line).find()){
            counter.addCodePlusComment();
        }
        else if (patternOfSlashStar.matcher(line).find()){
            if (line.startsWith("/*"))
                counter.addComment();
            else
                counter.addCodePlusComment();
            flagOfCommenting = 1;
        }
        else
            counter.addCode();
    }

    private void decideType(CodeCounter counter, String line, BufferedReader br){
        flagOfCommenting = 0;
        while((line = counter.readMyLine(br))!= null){
            line = line.trim();
            // 匹配空行
            if (flagOfCommenting == 0){
                countOutOfCommenting(counter, line);
                // if (line.length() == 0){
                //     counter.addBlank();
                // }
                // else if (line.startsWith("//")){
                //     counter.addComment();
                // }
                // else if (patternOfDoubleSlash.matcher(line).find()){
                //     counter.addCodePlusComment();
                // }
                // else if (patternOfSlashStar.matcher(line).find()){
                //     if (line.startsWith("/*"))
                //         counter.addComment();
                //     else
                //         counter.addCodePlusComment();
                //     flagOfCommenting = 1;
                // }
                // else
                //     counter.addCode();
            } else{
                m = patternOfStarSlash.matcher(line);
                if(m.find()){
                    countOutOfCommenting(counter, line);
                    flagOfCommenting = 0;
                    System.out.println(m.start());
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