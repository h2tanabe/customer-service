package h2.app.customer.enums;

import org.seasar.doma.Domain;

/**
 * ステータスのドメインクラスです。
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum OpptunityStatusType {

    /** 新規 */
	NEW("1"),
    /** 確定低 */
    LOW("2"),
    /** 確定高 */
    HIGH("3"),
    /** 達成 */
    COMPLETE("4")
    ;

    private final String value;

    private OpptunityStatusType(String value) {
        this.value = value;
    }

    public static OpptunityStatusType of(String value) {
        for (OpptunityStatusType v : OpptunityStatusType.values()) {
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
