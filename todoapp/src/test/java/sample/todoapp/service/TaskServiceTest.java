package sample.todoapp.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sample.todoapp.converter.TaskConverter;
import sample.todoapp.domain.model.task.Task;
import sample.todoapp.dto.CreateTaskDTO;
import sample.todoapp.exception.GlobalException;
import sample.todoapp.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskService taskService;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private TaskConverter taskConverter;

	@Test
	public void it_should_get_one() {

		// given
		String taskId = "123xyz";

		var task = new Task("ozdilbulent1@gmail.com", "new task 1", "Hello my first task 1");

		Mockito.when(taskRepository.findById(taskId)).thenReturn(task);

		// when
		var getOne = taskService.getOne(taskId);

		// then
		assertEquals(getOne, task);
		Mockito.verify(taskRepository).findById(taskId);
	}

	@Test
	public void it_should_get_all_by_id() {

		// given
		String userId = "1";
		var tasks = Arrays.asList(
				new Task("ozdilbulent1@gmail.com", "new task 1", "Hello my first task 1"),
				new Task("ozdilbulent2@gmail.com", "new task 2", "Hello my first task 2"),
				new Task("ozdilbulent3@gmail.com", "new task 3", "Hello my first task 3"));

		Mockito.when(taskRepository.findAllByUserId(userId)).thenReturn(tasks);

		// when
		var allResults = taskService.getAllByUserId(userId);

		// then
		assertArrayEquals(allResults.toArray(), tasks.toArray());
		Mockito.verify(taskRepository).findAllByUserId(userId);
	}

	@Test
	public void it_should_create_new_task() {

		// given
		var dto = new CreateTaskDTO("ozdilbulent@gmail.com", "new task", "Hello my first task");
		var task = new Task("ozdilbulent@gmail.com", "new task", "Hello my first task");

		Mockito.when(taskConverter.apply(dto)).thenReturn(task);

		// when
		var createdTask = taskService.create(dto);

		// then
		assertEquals(createdTask, task);
		Mockito.verify(taskRepository).save(task);
	}

	@Test(expected = GlobalException.class)
	public void it_should_throw_error_when_task_not_found_in_editing() {

		// given
		String taskId = "123xyz";
		var dto = new CreateTaskDTO(taskId, "ozdilbulent@gmail.com", "new task", "Hello my first task");
		Task task = null;

		Mockito.when(taskRepository.findById(taskId)).thenReturn(task);

		// when
		taskService.edit(dto);
	}

	@Test
	public void it_should_edit_task() {

		// given
		String taskId = "123xyz";
		var dto = new CreateTaskDTO(taskId, "ozdilbulent@gmail.com", "new task", "Hello my first task");
		var task = new Task("ozdilbulent@gmail.com", "new task", "Hello my first task");
		task.setId(taskId);

		Mockito.when(taskRepository.findById(dto.getTaskId())).thenReturn(task);

		// when
		var editTask = taskService.edit(dto);

		// then
		assertEquals(editTask, task);
		Mockito.verify(taskRepository).save(task);
	}

	@Test
	public void it_should_update_iscompleted_field_as_true() {

		// given
		var task = new Task("ozdilbulent@gmail.com", "new task", "Hello my first task");
		task.setId("123xyz");

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(task);

		// when
		boolean retval = taskService.setTaskAsCompleted(task.getId());

		// then
		assertEquals(true, retval);
		Mockito.verify(taskRepository).update(task);
	}

	@Test
	public void it_should_delete() {

		// given
		var task = new Task("ozdilbulent@gmail.com", "new task", "Hello my first task");
		task.setId("123xyz");

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(task);

		// when
		boolean retval = taskService.delete(task.getId());

		// then
		assertTrue(retval);
		Mockito.verify(taskRepository).delete(task.getId());
	}

	// so on and ...
}
