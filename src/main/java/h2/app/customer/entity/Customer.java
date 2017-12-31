package h2.app.customer.entity;

import java.time.LocalDate;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import h2.app.customer.enums.GenderType;

/**
 * 顧客テーブルのエンティティクラスです。
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 顧客名
     */
    public String name;

    /**
     * 顧客名カナ
     */
    public String nameKana;

    /**
     * 誕生日
     */
    public LocalDate birthDate;

    /**
     * 郵便番号
     */
    public String postalCode;

    /**
     * 住所
     */
    public String address;

    /**
     * 電話番号
     */
    public String tel;

    /**
     * 性別
     */
    public GenderType gender;

    /**
     * メールアドレス
     */
    public String mail;

}
