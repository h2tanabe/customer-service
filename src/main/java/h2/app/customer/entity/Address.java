package h2.app.customer.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Table;

/**
 * 顧客テーブルのエンティティクラスです。
 */
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	/**
	 * 旧郵便番号
	 */
	public Integer oldZip;

	/**
	 * 県コード
	 */
	public Integer kenCd;

	/**
	 * 郵便番号
	 */
	public Integer zip;

	/**
	 * 都道府県
	 */
	public String kenName;

	/**
	 * 都道府県カナ
	 */
	public String kenFuri;

	/**
	 * 市区町村
	 */
	public String cityName;

	/**
	 * 市区町村カナ
	 */
	public String cityFuri;

	/**
	 * 町域
	 */
	public String townName;

	/**
	 * 町域カナ
	 */
	public String townFuri;

}
