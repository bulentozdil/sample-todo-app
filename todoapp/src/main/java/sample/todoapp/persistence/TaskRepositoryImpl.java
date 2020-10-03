package sample.todoapp.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;

import org.springframework.stereotype.Component;

import com.couchbase.client.core.error.DocumentNotFoundException;

import sample.todoapp.domain.model.task.Task;
import sample.todoapp.domain.model.task.TaskRepository;
import sample.todoapp.exception.GlobalException;

@Component
public class TaskRepositoryImpl implements TaskRepository {

	private CouchbaseTemplate couchbaseTemplate;

	@Autowired
	public TaskRepositoryImpl(
			@Qualifier("taskCouchbaseTemplate") Supplier<CouchbaseTemplate> supplier) {
		couchbaseTemplate = supplier.get();
	}

	@Override
	public Task save(
			Task entity) {
		couchbaseTemplate.insertById(Task.class).one(entity);
		return entity;
	}

	@Override
	public Task update(
			Task entity) {
		couchbaseTemplate.upsertById(Task.class).one(entity);
		return entity;
	}

	@Override
	public Task findById(
			String id) {
		try {
			return couchbaseTemplate.findById(Task.class).one(id);
		} catch (Exception e) {
			if (e.getCause() instanceof DocumentNotFoundException) {
				return null;
			} else {
				throw new GlobalException(e.getMessage(), "TASK_REPOSITORY_ERROR");
			}
		}
	}

	@Override
	public List<Task> findAllByUserId(
			String userId) {
		return couchbaseTemplate.findByQuery(Task.class).matching(new Query().addCriteria(QueryCriteria.where("userId").eq(userId))).all();
	}

	@Override
	public Task findOneByIdAndUserId(
			String id,
			String userId) {
		try {
			return couchbaseTemplate.findByQuery(Task.class).matching(new Query().addCriteria(QueryCriteria.where("userId").eq(userId).and(QueryCriteria.where("META(id)")).eq(id))).firstValue();
		} catch (Exception e) {
			if (e.getCause() instanceof DocumentNotFoundException) {
				return null;
			} else {
				throw new GlobalException(e.getMessage(), "TASK_REPOSITORY_ERROR");
			}
		}
	}
}
