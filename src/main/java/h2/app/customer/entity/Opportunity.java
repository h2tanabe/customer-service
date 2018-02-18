package h2.app.customer.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import h2.app.customer.enums.OpptunityStatusType;

/**
 * 商談テーブルのエンティティクラスです。
 */
@Entity
@Table(name = "opportunities")
public class Opportunity extends BaseEntity {

	/**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     *商談名
     */
    public String name;

    /**
     * 数量
     */
    public Long ammount;


    /**
     * 顧客ID
     */
    public Long accountId;

    /**
     * 確度
     */
    public OpptunityStatusType status;

}
