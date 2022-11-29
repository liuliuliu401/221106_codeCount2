package com.example;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
 
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
 
class AppTest{
 
//   @DisplayName("Should calculate the correct sum")
    @ParameterizedTest
    @MethodSource("myMethod")
    void test(String fileName, int[] result) {
        int[] realResult = CodeCounter.readAndCount(fileName);
        // System.out.println(fileName);
        // System.out.println(realResult);
        // for(int i=0; i<4; i++)
        assertArrayEquals(result, CodeCounter.readAndCount(fileName));
    }
 
    private static Stream<Arguments> myMethod() {
        String rootPath = "C:\\Users\\SubFunction Residue\\Documents\\AAA My Scripts" + 
        "\\Java Scripts\\221106_codeCount2\\demo\\test_code\\boundary_test\\";
        int[] predicted01 = {0, 0, 0, 1};
        int[] predicted02 = {0, 0, 1, 0};
        int[] predicted03 = {0, 0, 1, 1};
        int[] predicted04 = {0, 1, 0, 0};
        int[] predicted05 = {0, 1, 0, 1};
        int[] predicted06 = {0, 1, 1, 0};
        int[] predicted07 = {0, 1, 1, 1};
        int[] predicted08 = {1, 0, 0, 0};
        int[] predicted09 = {1, 0, 0, 1};
        int[] predicted10 = {1, 0, 1, 0};
        int[] predicted11 = {1, 0, 1, 1};
        int[] predicted12 = {1, 1, 0, 0};
        int[] predicted13 = {1, 1, 0, 1};
        int[] predicted14 = {1, 1, 1, 0};
        int[] predicted15 = {1, 1, 1, 1};
        return Stream.of(
            Arguments.of(rootPath + "test01.java", predicted01),
            Arguments.of(rootPath + "test02.java", predicted02),
            Arguments.of(rootPath + "test03.java", predicted03),
            Arguments.of(rootPath + "test04.java", predicted04),
            Arguments.of(rootPath + "test05.java", predicted05),
            Arguments.of(rootPath + "test06.java", predicted06),
            Arguments.of(rootPath + "test07.java", predicted07),
            Arguments.of(rootPath + "test08.java", predicted08),
            Arguments.of(rootPath + "test09.java", predicted09),
            Arguments.of(rootPath + "test10.java", predicted10),
            Arguments.of(rootPath + "test11.java", predicted11),
            Arguments.of(rootPath + "test12.java", predicted12),
            Arguments.of(rootPath + "test13.java", predicted13),
            Arguments.of(rootPath + "test14.java", predicted14),
            Arguments.of(rootPath + "test15.java", predicted15)
        );
    }
}
