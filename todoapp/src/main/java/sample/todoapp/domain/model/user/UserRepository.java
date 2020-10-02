package sample.todoapp.domain.model.user;

public interface UserRepository {

	/**
	 * @param entity
	 * @return
	 */
	User save(
			User entity);
	
	/**
	 * @param id
	 * @return
	 */
	User findById(String id);
}
