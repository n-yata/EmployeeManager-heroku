package util;

import java.util.regex.Pattern;

/**
 * バリデータのutilクラス
 * @author yata1
 *
 */
public class Validator {
    /**
     * 文字数が制限以内か確認
     * @param str
     * @param maxSize
     * @return
     */
    public static boolean validateStringSize(String str, int maxSize) {
        if (str.getBytes().length > maxSize) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * nullまたは空文字でないか確認
     * @param str
     * @return
     */
    public static boolean validateRequired(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 引数にした正規表現と一致するか確認
     * @param str
     * @param regex
     * @return
     */
    public static boolean validateRegex(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        if (p.matcher(str).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 空文字をOKとする
     * @param str
     * @param regex
     * @return
     */
    public static boolean validateRegexAllowsBlank(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        if (str.equals("")) {
            return true;
        }
        if (p.matcher(str).matches()) {
            return true;
        }
        return false;
    }

    /**
     * zipのパターンが一致するか確認
     * @param str
     * @return
     */
    public static boolean validateZip(String str) {
        if (str.matches("^[0-9]{3}-[0-9]{4}$")) {
            return true;
        }
        return false;
    }

    /**
     * 空文字をOKとする
     * @param str
     * @return
     */
    public static boolean validateZipAllowsBlank(String str) {
        if (str.equals("")) {
            return true;
        }
        if (str.matches("^[0-9]{3}-[0-9]{4}$")) {
            return true;
        }
        return false;
    }

    /**
     * dateのパターンが一致するか確認
     * @param str
     * @return
     */
    public static boolean validateDate(String str) {
        if (str.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            return true;
        }
        return false;
    }

    /**
     * 空文字をOKとする
     * @param str
     * @return
     */
    public static boolean validateDateAllowsBlank(String str) {
        if (str.equals("")) {
            return true;
        }
        if (str.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            return true;
        }
        return false;
    }

    /**
     * ageのパターンが一致するか確認
     * @param str
     * @return
     */
    public static boolean validateNumber(String str) {
        if (!str.matches("^[0-9]+$")) {
            return false;
        }
        if (str.matches("^0+[0-9]+$")) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 空文字をOKとする
     * @param str
     * @return
     */
    public static boolean validateNumberAllowsBlank(String str) {
        if (str.equals("")) {
            return true;
        }
        if (!str.matches("^[0-9]+$")) {
            return false;
        }
        if (str.matches("^0+[0-9]+$")) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Mサイズのファイル
     * @param size
     * @param max
     * @return
     */
    public static boolean validateFileSize(long size, long max) {
        if (size > max) {
            return false;
        } else {
            return true;
        }
    }
}
