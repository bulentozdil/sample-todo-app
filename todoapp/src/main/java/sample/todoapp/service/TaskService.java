package sample.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.todoapp.converter.TaskConverter;
import sample.todoapp.domain.model.task.Task;
import sample.todoapp.dto.CreateTaskDTO;
import sample.todoapp.exception.GlobalException;
import sample.todoapp.repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	private TaskConverter taskConverter;

	@Autowired
	public TaskService(TaskRepository taskRepository, TaskConverter taskConverter) {
		this.taskRepository = taskRepository;
		this.taskConverter = taskConverter;
	}

	public List<Task> getAllByUserId(String userId) {
		return taskRepository.findAllByUserId(userId);
	}

	public Task getOne(String taskId, String userId) {
		return taskRepository.findOneByIdAndUserId(taskId, userId);
	}

	public Task create(CreateTaskDTO dto) {
		var task = taskConverter.apply(dto);
		taskRepository.save(task);
		return task;
	}

	public Task edit(CreateTaskDTO dto) {
		var task = taskRepository.findById(dto.getTaskId());
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		task.applyChanges(dto.getName(), dto.getDescription());
		taskRepository.update(task);
		return task;
	}

	public boolean setTaskAsCompleted(String taskId) {
		var task = taskRepository.findById(taskId);
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		task.setAsCompleted();
		taskRepository.update(task);
		return task.getIsCompleted();
	}

	public boolean delete(String taskId) {
		var task = taskRepository.findById(taskId);
		if (task == null) {
			throw new GlobalException("The task unable to found", "TASK_UNABLE_FOUND");
		}
		taskRepository.delete(taskId);
		return true;
	}
}
