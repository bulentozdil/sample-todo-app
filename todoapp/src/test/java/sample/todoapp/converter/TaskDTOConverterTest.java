package sample.todoapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.domain.model.task.Task;

@RunWith(SpringRunner.class)
public class TaskDTOConverterTest {

	@Test
	public void is_should_expect_converting_task_to_task_dto() {

		var converter = new TaskDTOConverter();
		var dto = converter.apply(new Task("1", "task", "description"));
		
		assertEquals("1", dto.getUserId());
		assertEquals("task", dto.getName());
		assertEquals("description", dto.getDescription());
	}
}
