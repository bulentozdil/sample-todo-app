package sample.todoapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.dto.CreateTaskDTO;

@RunWith(SpringRunner.class)
public class TaskConverterTest {
	
	@Test
	public void it_should_expect_converting_create_task_dto_to_task_object() {
		
		var converter = new TaskConverter();
		var task = converter.apply(new CreateTaskDTO("1", "ahmet", "description"));
		
		assertEquals("1", task.getUserId());
		assertEquals("ahmet", task.getName());
		assertEquals("description", task.getDescription());
	}
}
