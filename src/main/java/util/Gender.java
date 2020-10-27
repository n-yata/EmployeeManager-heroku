package util;

/**
 * 性別の列挙クラス
 * @author yata1
 *
 */
public enum Gender {
    MAN("man", "男"), WOMAN("woman", "女"), OTHER("other", "その他");

    private final String code_;
    private final String text_;

    private Gender(String code, String text) {
        code_ = code;
        text_ = text;
    }

    public String getCode() {
        return code_;
    }

    public String getText() {
        return text_;
    }
}
