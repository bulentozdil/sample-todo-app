package sample.todoapp.persistence;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;

import sample.todoapp.domain.model.user.User;
import sample.todoapp.domain.model.user.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository {

	private CouchbaseTemplate couchbaseTemplate;

	/**
	 * @param supplier
	 */
	@Autowired
	public UserRepositoryImpl(
			@Qualifier("userCouchbaseTemplate") Supplier<CouchbaseTemplate> supplier) {
		this.couchbaseTemplate = supplier.get();
	}

	@Override
	public User save(
			User entity) {
		couchbaseTemplate.insertById(User.class).one(entity);
		return entity;
	}

	@Override
	public User findById(
			String id) {
		return couchbaseTemplate.findById(User.class).one(id);
	}
}
