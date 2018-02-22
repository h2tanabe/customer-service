package h2.app.customer.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ユーザテーブルのエンティティクラスです。
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    /**
     * ログインID
     */
	@Id
    public String userName;

    /**
     * パスワード
     */
	@JsonIgnore
    public String encodedPassword;

}
