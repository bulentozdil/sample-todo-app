package sample.todoapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.domain.model.task.Task;

@RunWith(SpringRunner.class)
public class TaskTest {

	
	@Test
	public void it_should_be_apply_changes() {
		
		Task task = new Task("1", "new task", "description");
		task.applyChanges("name task updated", "descriptipn updated");
		
		assertEquals("name task updated", task.getName());
		assertEquals("descriptipn updated", task.getDescription());
	}
	
	@Test
	public void it_should_be_as_completed() {
		
		Task task = new Task("1", "new task", "description");
		task.setAsCompleted();
		
		assertEquals(true, task.getIsCompleted());
	}
	
	@Test
	public void it_should_be_as_deleted() {
		
		Task task = new Task("1", "new task", "description");
		task.setAsDeleted();
		
		assertEquals(true, task.getIsDeleted());
	}
}
