package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
 
class MethodSourceExampleTest {
 
//   @DisplayName("Should calculate the correct sum")
  @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
  @MethodSource("sumProvider")
  void sum(int a, int b, int sum) {
    assertEquals(sum, a + b);
  }
 
  private static Stream<Arguments> sumProvider() {
    return Stream.of(
        Arguments.of(1, 1, 2),
        Arguments.of(2, 3, 5)
    );
  }
  
}
