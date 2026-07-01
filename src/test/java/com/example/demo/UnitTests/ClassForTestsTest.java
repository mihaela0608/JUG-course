package com.example.demo.UnitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassForTestsTest {

    private ClassForTests classForTests;

    private static Stream<Arguments> provideNumbersForAdding() {
        return Stream.of(
                Arguments.of(5, 3, 8),
                Arguments.of(-3, -2, -5),
                Arguments.of(0, 0, 0)
        );
    }

    private static Stream<Arguments> provideAgesForCategory() {
        return Stream.of(
                Arguments.of(12, "child"),
                Arguments.of(17, "teen"),
                Arguments.of(45, "adult"),
                Arguments.of(70, "senior")
        );
    }

    @BeforeEach
    public void setUp(){
        classForTests = new ClassForTests();
    }

    @ParameterizedTest
    @MethodSource("provideNumbersForAdding")
    public void testAdd_returnsRightValue(int a, int b, int result){
        Assertions.assertEquals(result, classForTests.add(a, b));
    }

    @Test
    public void testDivision_returnsDividedNumber(){
        Assertions.assertEquals(4, classForTests.divide(8, 2));
        Assertions.assertEquals(0, classForTests.divide(0, 7));
    }

    @Test
    public void testDivision_throwsWhenDividedBy0(){
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> classForTests.divide(4, 0));

        Assertions.assertEquals("Division by zero is not allowed", arithmeticException.getMessage());
    }

    @Test
    public void testIsPrime_checksPrimeNumber(){
        Assertions.assertFalse(classForTests.isPrime(1));
        Assertions.assertFalse(classForTests.isPrime(49));
        Assertions.assertTrue(classForTests.isPrime(7));
    }

    @Test
    public void testFindMax_returnsMaxNumber(){
        List<Integer> integers = List.of(1, 5, 9, 3, 7, 88);
        Assertions.assertEquals(88, classForTests.findMax(integers));
    }

    @Test
    public void testFindMax_throwsWhenEmptyListOrNull(){
        IllegalArgumentException whenEmpty = assertThrows(IllegalArgumentException.class, () -> classForTests.findMax(List.of()));
        IllegalArgumentException whenNull = assertThrows(IllegalArgumentException.class, () -> classForTests.findMax(null));

        Assertions.assertEquals("List must not be null or empty", whenEmpty.getMessage());
        Assertions.assertEquals("List must not be null or empty", whenNull.getMessage());
    }

    @Test
    public void testReverseString_whenNull(){
        Assertions.assertNull(classForTests.reverseString(null));
    }

    @Test
    public void testReverseString_reverseText(){
        Assertions.assertEquals("enalp", classForTests.reverseString("plane"));
    }

    @Test
    public void testIsPalindrome_whenValidWord(){
        Assertions.assertTrue(classForTests.isPalindrome("bob"));
    }

    @Test
    public void testIsPalindrome_withWhiteSpaces(){
        Assertions.assertTrue(classForTests.isPalindrome("b    ob"));
    }

    @Test
    public void testIsPalindrome_whenWordIsNull(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> classForTests.isPalindrome(null));
        Assertions.assertEquals("Input must not be null", illegalArgumentException.getMessage());
    }

    @Test
    public void testFactorial_withPositiveNumber(){
        Assertions.assertEquals(24, classForTests.factorial(4));
    }

    @Test
    public void testFactorial_throwsWithNegativeNumber(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> classForTests.factorial(-8));
        Assertions.assertEquals("Factorial is not defined for negative numbers", illegalArgumentException.getMessage());
    }

    @Test
    public void testCategorizeAge_throwsWhenNegativeAge(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> classForTests.categorizeAge(-8));
        Assertions.assertEquals("Age cannot be negative", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideAgesForCategory")
    public void testCategorizeAge_returnsCorrectValue(int age, String result){
        Assertions.assertEquals(result, classForTests.categorizeAge(age));
    }

}
