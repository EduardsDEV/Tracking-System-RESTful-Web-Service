package dk.das.utils;

import org.junit.jupiter.api.Test;

import static dk.das.utils.QrCodeUtils.convertIntToStringCode;
import static dk.das.utils.QrCodeUtils.isValidQrCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QrCodeUtilsTest {
    @Test
    void getLettersFromQrCodeTest() {
    }

    @Test
    void getNumberFromQrCodeTest() {
    }

    @Test
    void convertIntToStringCodeTest() {
        assertTrue(convertIntToStringCode(59).equals("0059"));
        assertTrue(convertIntToStringCode(590).equals("0590"));
        assertTrue(convertIntToStringCode(12345).equals("12345"));
    }

    @Test
    void isValidQrCodeTest() {
        for (int i = 1; i < 10_000; i++) {
            final String digitsCode = convertIntToStringCode(i);
            assertTrue(isValidQrCode("AA" + digitsCode));
        }
        assertFalse(isValidQrCode("A1234A"));
        assertFalse(isValidQrCode("AAA1234"));
        assertFalse(isValidQrCode("1234A"));
        assertFalse(isValidQrCode("1234AA"));
        assertFalse(isValidQrCode("1234"));
        assertFalse(isValidQrCode("1234567"));
        assertFalse(isValidQrCode("AAAAAA"));
        assertFalse(isValidQrCode("AA0000"));
        assertFalse(isValidQrCode("000000"));
        assertFalse(isValidQrCode("0000AA"));
        assertFalse(isValidQrCode("Aa1234"));
        assertFalse(isValidQrCode("A1234a"));
        assertFalse(isValidQrCode("AA123)"));
    }

}