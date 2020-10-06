package sample.todoapp.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryOptions;

import sample.todoapp.config.Constants;
import sample.todoapp.config.CustomJsonSerializer;
import sample.todoapp.domain.model.task.Task; 
import sample.todoapp.exception.GlobalException;

@Component
public class TaskRepository extends Repository {
 
	/**
	 * @param entity
	 * @return
	 */
	public Task save(
			Task entity) {
		bucket().defaultCollection().insert(entity.getId(), entity);
		return entity;
	}
 
	/**
	 * @param entity
	 * @return
	 */
	public Task update(
			Task entity) {
		bucket().defaultCollection().upsert(entity.getId(), entity);
		return entity;
	}
 
	/**
	 * @param id
	 * @return
	 */
	public Task findById(
			String id) {
		try {
			var result = cluster.bucket("task").defaultCollection().get(id);
			return result.contentAs(Task.class);
		} catch (Exception e) {
			if (e.getCause() instanceof DocumentNotFoundException) {
				return null;
			} else {
				throw new GlobalException(e.getMessage(), "TASK_REPOSITORY_ERROR");
			}
		}
	}
 
	/**
	 * @param userId
	 * @return
	 */
	public List<Task> findAllByUserId(
			String userId) {
		return cluster().query("select * from task where userId = $userId", QueryOptions.queryOptions()
				.serializer(new CustomJsonSerializer())
				.parameters(JsonObject.create().put("userId", userId)))
				.rowsAs(Task.class);
	}
 
	/**
	 * @param id
	 * @param userId
	 * @return
	 */
	public Task findOneByIdAndUserId(
			String id,
			String userId) {
		return cluster().query("select * from task where userId = $userId and id = $id",
						QueryOptions.queryOptions()
							.serializer(new CustomJsonSerializer())
							.parameters(JsonObject.create().put("userId", userId).put("id", id)))
				.rowsAs(Task.class)
					.stream()
					.findFirst()
					.orElse(null);
	}

	@Override
	public Bucket bucket() {
		return cluster().bucket(Constants.BUCKET_TASK);
	}
}
