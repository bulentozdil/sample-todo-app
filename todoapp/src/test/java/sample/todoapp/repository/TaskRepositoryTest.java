package sample.todoapp.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.todoapp.config.Constants;
import sample.todoapp.config.CouchbaseConfig;
import sample.todoapp.domain.model.task.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

	private static CouchbaseContainerStarter starter;

	@Autowired
	private TaskRepository taskRepository;

	@AfterClass
	public static void after() {
		starter.stop();
	}

	@Test
	public void it_should_save() {

		Task task = new Task("1", "new task", "description");
		taskRepository.save(task);
	}

	@Test
	public void it_should_update() {

		Task task = new Task("1", "new task", "description");
		taskRepository.save(task);

		task.applyChanges("new task 2", "description 2");
		taskRepository.update(task);
		
		assertEquals("new task 2", task.getName());
		assertEquals("description 2", task.getDescription());
	}

	@Test
	public void it_should_get_all_by_userid() {
		
		Task task = new Task("1", "new task", "description");
		taskRepository.save(task);

		var tasks = taskRepository.findAllByUserId(task.getUserId());

		assertNotNull(tasks);
		assertTrue(tasks.size() > 0);
	}

	@Test
	public void it_should_get_by_id() {

		Task task = new Task("1", "new task", "description");
		taskRepository.save(task);

		var createdTask = taskRepository.findById(task.getId());

		assertNotNull(createdTask);
	}

	@Test
	public void it_should_get_one_by_id_and_userid() {

		Task task = new Task("1", "new task", "description");
		taskRepository.save(task);

		var createdTask = taskRepository.findOneByIdAndUserId(task.getId(), task.getUserId());

		assertNotNull(createdTask);
	}

	@TestConfiguration
	static class TestContainerConfig {

		@Autowired
		private CouchbaseConfig config;

		@PostConstruct
		public void init() {
			starter = new CouchbaseContainerStarter();
			starter.start(Constants.BUCKET_TASK, 100);
			starter.replaceConfigProperties(config);
		}
	}
}
