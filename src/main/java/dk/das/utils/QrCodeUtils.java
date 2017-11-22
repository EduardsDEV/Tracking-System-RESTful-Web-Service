package dk.das.utils;

public class QrCodeUtils {
    private static final int NUMBER_OF_LETTERS_IN_QR_CODE = 2;
    private static final int QR_CODE_LENGTH = 6;

    public static boolean isValidQrCode(String code) {
        if (code.length() != QR_CODE_LENGTH) {
            return false;
        }
        final char[] chars = code.toCharArray();
        for (int i = NUMBER_OF_LETTERS_IN_QR_CODE; i < QR_CODE_LENGTH; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        if (code.substring(NUMBER_OF_LETTERS_IN_QR_CODE).equals("0000")) {
            return false;
        }
        for (int i = 0; i < NUMBER_OF_LETTERS_IN_QR_CODE; i++) {
            if (!Character.isUpperCase(chars[i])) { // checks that is a letter as well
                return false;
            }
        }
        return true;
    }

    public static String convertIntToStringCode(int code) {
        StringBuilder s = new StringBuilder(String.valueOf(code));
        while (s.length() < QR_CODE_LENGTH - NUMBER_OF_LETTERS_IN_QR_CODE) {
            s.insert(0, "0");
        }
        return s.toString();
    }

    public static String getLettersFromQrCode(String code) {
        return code.substring(0, NUMBER_OF_LETTERS_IN_QR_CODE);
    }

    public static int getNumberFromQrCode(String code) {
        return Integer.valueOf(code.substring(NUMBER_OF_LETTERS_IN_QR_CODE));
    }
}
