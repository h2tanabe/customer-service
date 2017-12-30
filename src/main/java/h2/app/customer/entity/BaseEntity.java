package h2.app.customer.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(listener = BaseEntityListener.class, naming = NamingType.SNAKE_LOWER_CASE)
public abstract class BaseEntity {

    /**
     * 作成日時
     */
    @Column(updatable = false)
    public LocalDateTime createdAt;

    /**
     * 作成者
     */
    @Column(updatable = false)
    public String createdBy;

    /**
     * 更新日時
     */
    public LocalDateTime updatedAt;

    /**
     * 更新者
     */
    public String updatedBy;

}