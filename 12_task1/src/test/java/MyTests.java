import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyTests {
    @Test
    void testNullString() {
        assertThrows(NullPointerException.class, () -> Integer.decode(null));
    }

    @Test
    void testZeroLengthString() {
        assertThrows(NumberFormatException.class, () -> Integer.decode(""));
    }

    @Test
    void testNonConvertibleStringToInteger() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("Hi, I'm a Integer")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("19 years old")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("#13 years old")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("023 years old"))
        );
    }

    @Test
    void testDecimalNumeralWithoutSign() {
        assertEquals(Integer.valueOf(1234), Integer.decode("1234"));
    }

    @Test
    void testDecimalNumeralWithWrongSymbol() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("A9067"));
    }

    @Test
    void testDecimalNumeralWithPositiveSign() {
        assertEquals(Integer.valueOf(1234), Integer.decode("+1234"));
    }

    @Test
    void testDecimalNumeralWithNegativeSign() {
        assertEquals(Integer.valueOf(-1234), Integer.decode("-1234"));
    }

    @Test
    void testOctalDigitsWithoutSign() {
        assertEquals(Integer.valueOf(668), Integer.decode("01234"));
    }

    @Test
    void testOctalDigitsWithWrongSymbol() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("0A")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("09"))
        );
    }

    @Test
    void testOctalDigitsWithPositiveSign() {
        assertEquals(Integer.valueOf(668), Integer.decode("+01234"));
    }

    @Test
    void testOctalDigitsWithNegativeSign() {
        assertEquals(Integer.valueOf(-668), Integer.decode("-01234"));
    }

    @Test
    void testHexDigitsWithoutSignStartsWith0x() {
        assertEquals(Integer.valueOf(74565), Integer.decode("0x12345"));
    }

    @Test
    void testHexDigitsWithoutSignStartsWith0X() {
        assertEquals(Integer.valueOf(74565), Integer.decode("0X12345"));
    }

    @Test
    void testHexDigitsWithoutSignStartsWithSharp() {
        assertEquals(Integer.valueOf(74565), Integer.decode("#12345"));
    }

    @Test
    void testWrongNumeralSystem() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("0#123"));
    }

    @Test
    void testHexDigitsWithWrongSymbol() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("0xABCDQ"));
    }

    @Test
    void testHexDigitsWithDifferentCases() {
        assertAll(
                () -> assertEquals(Integer.valueOf(43981), Integer.decode("0xABCD")),
                () -> assertEquals(Integer.valueOf(43981), Integer.decode("0Xabcd")),
                () -> assertEquals(Integer.valueOf(43981), Integer.decode("#AbCd"))
        );
    }

    @Test
    void testHexDigitsWithPositiveSign() {
        assertEquals(Integer.valueOf(74565), Integer.decode("+0x12345"));
    }

    @Test
    void testHexDigitsWithNegativeSign() {
        assertEquals(Integer.valueOf(-74565), Integer.decode("-0x12345"));
    }

    @Test
    void testDecimalNumeralWIthWrongPositionOfSign() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("1-2344")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("1+2344"))
        );
    }

    @Test
    void testHexDigitsWithWrongPositionOfSign() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("#-12344")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("0x+12344")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("0-X12344")));
    }

    @Test
    void testOctalDigitsWithWrongPositionOfSign() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("0-12344")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("0+12344"))
        );
    }

    @Test
    void testSingleZero() {
        assertEquals(Integer.valueOf(0), Integer.decode("0"));
    }

    @Test
    void testSingleSign() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("+")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("-"))
        );
    }


    @Test
    void testOutsideOfInteger() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("2147483648")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("-2147483649"))
        );
    }
}
