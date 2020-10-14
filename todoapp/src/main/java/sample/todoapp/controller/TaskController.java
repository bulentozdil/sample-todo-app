package sample.todoapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sample.todoapp.converter.TaskDTOConverter;
import sample.todoapp.dto.CreateTaskDTO;
import sample.todoapp.dto.TaskDTO;
import sample.todoapp.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskDTOConverter taskConverter;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>> getAllByUserId(
			@RequestParam String userId) {
		var data = taskService.getAllByUserId(userId);
		return ResponseEntity.ok(data.stream().map(m -> taskConverter.apply(m)).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
	public ResponseEntity<TaskDTO> getOne(
			@PathVariable String taskId,
			@RequestParam String userId) {
		var data = taskService.getOne(taskId, userId);
		return ResponseEntity.ok(taskConverter.apply(data));
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createTask(
			@RequestBody CreateTaskDTO dto) {
		taskService.create(dto);
		return ResponseEntity.ok(true);
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
	public ResponseEntity<TaskDTO> editTask(
			@PathVariable String taskId,
			@RequestBody CreateTaskDTO dto) {
		dto.setTaskId(taskId);
		var task = taskService.edit(dto);
		var result = taskConverter.apply(task);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/{taskId}/completed", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> completed(
			@PathVariable String taskId) {
		boolean retval = taskService.setTaskAsCompleted(taskId);
		return ResponseEntity.ok(retval);
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleted(
			@PathVariable String taskId) {
		boolean retval = taskService.delete(taskId);
		return ResponseEntity.ok(retval);
	}
}
