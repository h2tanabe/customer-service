package h2.app.customer.entity;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;

/**
 * 顧客テーブルのエンティティクラスです。
 */
@CsvBean(header=true)
public class AddressSuperCSV{

	/**
	 * 全国地方公共団体コード
	 */
	@CsvColumn(label = "全国地方公共団体コード", number = 1)
	public String allAreaCd;

	/**
	 * 旧郵便番号
	 */
	@CsvColumn(label = "旧郵便番号", number = 2)
	public String kenCd;

	/**
	 * 郵便番号
	 */
	@CsvColumn(label = "郵便番号", number = 3)
	public String zip;

	/**
	 * 都道府県名
	 */
	@CsvColumn(label = "都道府県名", number = 7)
	public String kenName;

	/**
	 * 都道府県名カナ
	 */
	@CsvColumn(label = "都道府県名カナ", number = 4)
	public String kenFuri;

	/**
	 * 市区町村名
	 */
	@CsvColumn(label = "市区町村名", number = 8)
	public String cityName;

	/**
	 * 市区町村名カナ
	 */
	@CsvColumn(label = "市区町村名カナ", number = 5)
	public String cityFuri;

	/**
	 * 町域名
	 */
	@CsvColumn(label = "町域名", number = 9)
	public String townName;

	/**
	 * 町域名カナ
	 */
	@CsvColumn(label = "町域名カナ", number = 6)
	public String townFuri;

	/**
	 * テキスト１
	 */
	@CsvColumn(label = "1", number = 10)
	public String text1;

	/**
	 * テキスト２
	 */
	@CsvColumn(label = "2", number = 11)
	public String text2;

	/**
	 * テキスト３
	 */
	@CsvColumn(label = "3", number = 12)
	public String text3;

	/**
	 * テキスト４
	 */
	@CsvColumn(label = "4", number = 13)
	public String text4;

	/**
	 * テキスト５
	 */
	@CsvColumn(label = "5", number = 14)
	public String text5;

	/**
	 * テキスト６
	 */
	@CsvColumn(label = "6", number = 15)
	public String text6;


}
