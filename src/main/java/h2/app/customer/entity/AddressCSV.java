package h2.app.customer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * 顧客テーブルのエンティティクラスです。
 */
@Data
@JsonPropertyOrder({"全国地方公共団体コード","旧郵便番号","郵便番号","都道府県名カナ","市区町村名カナ","町域名カナ","都道府県名","市区町村名","町域名","1","2","3","4","5","6"})
public class AddressCSV{

	/**
	 * 全国地方公共団体コード
	 */
	@JsonProperty("全国地方公共団体コード")
	public String allAreaCd;

	/**
	 * 県コード
	 */
	@JsonProperty("旧郵便番号")
	public String kenCd;

	/**
	 * 郵便番号
	 */
	@JsonProperty("郵便番号")
	public String zip;

	/**
	 * 都道府県
	 */
	@JsonProperty("都道府県名")
	public String kenName;

	/**
	 * 都道府県カナ
	 */
	@JsonProperty("都道府県名カナ")
	public String kenFuri;

	/**
	 * 市区町村
	 */
	@JsonProperty("市区町村名")
	public String cityName;

	/**
	 * 市区町村カナ
	 */
	@JsonProperty("市区町村名カナ")
	public String cityFuri;

	/**
	 * 町域
	 */
	@JsonProperty("町域名")
	public String townName;

	/**
	 * 町域
	 */
	@JsonProperty("町域名カナ")
	public String townFuri;

	/**
	 * テキスト１
	 */
	@JsonProperty("1")
	public String text1;

	/**
	 * テキスト２
	 */
	@JsonProperty("2")
	public String text2;

	/**
	 * テキスト３
	 */
	@JsonProperty("3")
	public String text3;

	/**
	 * テキスト４
	 */
	@JsonProperty("4")
	public String text4;

	/**
	 * テキスト５
	 */
	@JsonProperty("5")
	public String text5;

	/**
	 * テキスト６
	 */
	@JsonProperty("6")
	public String text6;


}
