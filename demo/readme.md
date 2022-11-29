# Basic introduction of codeCounter demo
## rule
This is a simple tool to calculate lines in java files as well as a homework project of Zhejiang University.So remember: you can use it but <b>DON'T COPY IT</b> for your homework!!!!!

Rule as follows:
In the program, the code is divided into 4 categories: 

<b>

1. blank, 

2. pure code, 

3. comment, 

4. code with comment
</b>. 

There into:
1. A <b>blank line</b> is a line that does not have any characters (except for newlines and tabs), and the line length is 0 when read. For example:
```java
(You can't see it, here is nothing)
```
2. <b>Pure code lines</b> refer to lines where only code has no comment symbols. For example:
```java
import java.util.regex.*;
```
3. <b>A pure comment line</b> means that there are only valid comments inside the line. Since there are two types of Java language annotation methods: single-line comment // and cross-line comment /* */, this class includes the following three forms:
(1) Pure single-line comments.
```java
 This is a comment line.
```
(2) Include only the beginning of the cross-line comment. As in line 1 below:
```java
/*
*/System.out.println(“comment ends here.”)
```
(3) The middle part of the cross-line comment. As in line 2 below:
```java
System.out.println(“comment starts here.”) /*
This is a comment line.
*/System.out.println(“comment ends here.”)
```

4. <b>Code + comment</b> line means that there is both code and comments in the line. For example, the following are the following:
(1) Code with single-line comments.
```java
System.out.println(“comment line here.”)  //This is a comment line.
```
(2) There is code and cross-line annotations in the line. For example, the following 3 lines of code meet this requirement:
```java
System.out.println(“comment starts here.”) /*
*/System.out.println(“between 2 comments.”) /*
*/System.out.println(“comment ends here.”)
```
## Validation
<b>CLOC tool</b> is a easy code statistics to calculate code information and to valid whether our tool is correct.Link here for stable version:
<https://github.com/AlDanial/cloc/releases/latest>

It should be noted that the <b>CLOC tool</b> only has the above 1-3 categories, and will classify 4 into one of the 2 and 3 categories. Therefore, when using cloc to assist automatic statistical verification, as long as the number of 2, 3 classes of cloc is greater than the output result of 2,3, the number of classes is defined as meeting the requirements. At the same time, the cloc tool will not count the space on the last line, but this tool will count, so there will be discrepancies in the space.

### How to start main
Open this folder: 
```
demo\src\main\java\com\example\CodeCounter.java
```

It starts in my computer like this, and the last param is the address of java doc to be calculated:
```
'c:\Users\SubFunction Residue\Documents\AAA My Scripts\Java Scripts\221106_codeCount2'; & 'D:\Program Files\Java\jdk-18.0.2.1\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Users\SubFunction Residue\Documents\AAA My Scripts\Java Scripts\221106_codeCount2\demo\target\classes' 'com.example.CodeCounter' 'demo\test_code\equivalence_class_test\test12.java'
```
Or run the two files for test
```
demo\src\test\java\com\example\AppTest.java
demo\src\test\java\com\example\EuqalityTest.java
```
but remember to change the rootPath in Line 23 and 24 to your path in your machine:
```
 String rootPath = "C:\\Users\\SubFunction Residue\\Documents\\AAA My Scripts" + 
        "\\Java Scripts\\221106_codeCount2\\demo\\test_code\\equivalence_class_test\\";
```
Cloc can be started in command line like this to valid my code:
```
cloc 'demo\src\main\java\com\example\CodeCounter.java'
```
Wish you to use it happily!