package sample.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.todoapp.converter.TaskConverter;
import sample.todoapp.domain.model.task.Task;
import sample.todoapp.domain.model.task.TaskRepository; 
import sample.todoapp.dto.CreateTaskDTO;
import sample.todoapp.exception.GlobalException;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	private TaskConverter taskConverter; 

	/**
	 * @param taskRepository
	 * @param taskConverter
	 */
	@Autowired
	public TaskService(
			TaskRepository taskRepository,
			TaskConverter taskConverter) {
		this.taskRepository = taskRepository;
		this.taskConverter = taskConverter;
	}

	/**
	 * @param userId
	 * @return
	 */
	public List<Task> getAllByUserId(
			String userId) {
		return taskRepository.findAllByUserId(userId);
	}

	/**
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public Task getOne(
			String taskId,
			String userId) {
		return taskRepository.findOneByIdAndUserId(taskId, userId);
	}

	/**
	 * @param realmId
	 * @param name
	 * @param description
	 * @param planDate
	 */
	public Task create(
			CreateTaskDTO dto) {
		var task = taskConverter.apply(dto);
		taskRepository.save(task);
		return task;
	}

	/**
	 * @param taskId
	 * @param name
	 * @param description
	 */
	public Task edit( 
			CreateTaskDTO dto) {
		var task = taskRepository.findById(dto.getTaskId());
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		task.applyChanges(dto.getName(), dto.getDescription());
		taskRepository.save(task);
		return task;
	}

	/**
	 * @param taskId
	 */
	public boolean setTaskAsCompleted(
			String taskId) {
		var task = taskRepository.findById(taskId);
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		task.setAsCompleted();
		taskRepository.update(task);
		return task.isCompleted();
	}

	/**
	 * @param taskId
	 */
	public boolean setTaskAsDeleted(
			String taskId) {
		var task = taskRepository.findById(taskId);
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		task.setAsDeleted();
		taskRepository.update(task);
		return task.isDeleted();
	}
}
