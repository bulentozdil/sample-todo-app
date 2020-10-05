package sample.todoapp.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sample.todoapp.domain.model.task.Task;

@RunWith(SpringRunner.class)
public class CustomJsonSerializerTest {

	/**
	 * 
	 */
	public ObjectMapper mapper;

	@Before
	public void init() {
		this.mapper = new ObjectMapper();
	}

	@Test
	public void it_should_serialize() {

		var serializer = new CustomJsonSerializer();
		var task = new Task("1", "new task", "description");

		var result = serializer.serialize(task);

		assertNotNull(result);
	}

	@Test
	public void it_should_deserialize() throws JsonProcessingException {

		var serializer = new CustomJsonSerializer();
		var task = new Task("1", "new task", "description");

		String body = mapper.writeValueAsString(task);

		var result = serializer.deserialize(Task.class, body.getBytes());

		assertNotNull(result);

	}
}
