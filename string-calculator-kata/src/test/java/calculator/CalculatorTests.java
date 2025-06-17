package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {

    @Test
    void emptyStringShouldReturnZero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add(""));
    }

    @Test
    void singleNumberShouldReturnValue() {
        StringCalculator calc = new StringCalculator();
        assertEquals(1, calc.add("1"));
    }

    @Test
    void multipleNumberShouldReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(10, calc.add("1,2,3,4"));
    }

    @Test
    void newLineBetweenNumbers() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    void customDelimiterShouldBeUsed() {
        StringCalculator calc = new StringCalculator();
        assertEquals(3, calc.add("//;\n1;2"));
    }
    @Test
    void negativeNumbersShouldThrowException() {
        StringCalculator calc = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-2,3,-4");
        });

        assertEquals("Negatives not allowed: -2, -4", exception.getMessage());
    }
    @Test
    void numberBiggerThan1000Ignored () {
        StringCalculator calc = new StringCalculator();
        int result = calc.add("2,1001,3");
        assertEquals(5, result); // 2 + 3 = 5; 1001 is ignored
    }
    @Test
    void multiCharacterDelimiter() { //Multi-character delimiter using [***]
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[***]\n1***2***3");// *** is the custom delimiter, The calculator should split by ***
        assertEquals(6, result);
    }
    @Test
    void multiCharacterDelimiter2() { // Multiple single-character delimiters like [*][%]
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }
    @Test
    void multiDelimiterLongLength() { //Allows any length and multiples like [**][%%]
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[**][%%]\n1**2%%3"));
    }

}
