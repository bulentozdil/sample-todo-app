package sample.todoapp.domain.model.task;

import java.util.List;

public interface TaskRepository {
	
	/**
	 * @param entity
	 * @return
	 */
	Task save(Task entity);
	
	/**
	 * @param entity
	 * @return
	 */
	Task update(Task entity);
	
	/**
	 * @param id
	 * @return
	 */
	Task findById(String id);

	/**
	 * @param userId
	 * @return
	 */
	List<Task> findAllByUserId(
			String userId);

	/**
	 * @param id
	 * @param userId
	 * @return
	 */
	Task findOneByIdAndUserId(
			String id,
			String userId);
}
