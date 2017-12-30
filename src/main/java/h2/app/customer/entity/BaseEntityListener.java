package h2.app.customer.entity;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityListener<T extends BaseEntity> implements EntityListener<T> {
	@Override
	public void preUpdate(T entity, PreUpdateContext<T> context) {
		entity.updatedAt = LocalDateTime.now();
		entity.updatedBy = "admin";
	}

	@Override
	public void preInsert(T entity, PreInsertContext<T> context) {
		entity.createdAt = LocalDateTime.now();
		entity.createdBy = "admin";
	}
}