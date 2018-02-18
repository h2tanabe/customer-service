package h2.app.customer.dto;

import org.seasar.doma.Entity;

import h2.app.customer.entity.Opportunity;

/**
 * 商談テーブルのエンティティクラスです。
 */
@Entity
public class OpportunityDto extends Opportunity {

    /**
     *顧客名
     */
    public String customerName;

    /**
     * 郵便番号
     */
    public String postalCode;


    /**
     * 顧客住所
     */
    public String address;


}
