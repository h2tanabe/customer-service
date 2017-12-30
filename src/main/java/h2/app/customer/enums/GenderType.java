package h2.app.customer.enums;

/**
 * 性別のドメインクラスです。
 */
public enum GenderType {

    /** 男性 */
    MALE("1"),
    /** 女性 */
    FEMALE("2");

    private final String value;

    private GenderType(String value) {
        this.value = value;
    }

    public static GenderType of(String value) {
        for (GenderType v : GenderType.values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getValue() {
        return value;
    }

}
