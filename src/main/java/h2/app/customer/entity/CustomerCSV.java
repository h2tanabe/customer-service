package h2.app.customer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * 顧客CSVのエンティティクラスです。
 */
@Data
@JsonPropertyOrder({"ID", "名前", "名前カナ", "郵便番号", "住所", "電話番号", "メールアドレス"})
public class CustomerCSV {

    /**
     * ID
     */
	@JsonProperty("ID")
    public Long id;

    /**
     * 顧客名
     */
	@JsonProperty("名前")
    public String name;

    /**
     * 顧客名カナ
     */
	@JsonProperty("名前カナ")
    public String nameKana;

    /**
     * 郵便番号
     */
	@JsonProperty("郵便番号")
    public String postalCode;

    /**
     * 住所
     */
	@JsonProperty("住所")
   public String address;

    /**
     * 電話番号
     */
	@JsonProperty("電話番号")
    public String tel;

    /**
     * メールアドレス
     */
	@JsonProperty("メールアドレス")
    public String mail;

}
