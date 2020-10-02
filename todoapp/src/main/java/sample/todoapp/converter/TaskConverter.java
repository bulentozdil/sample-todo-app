package sample.todoapp.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import sample.todoapp.domain.model.task.Task;
import sample.todoapp.dto.CreateTaskDTO;

@Component
public class TaskConverter implements Function<CreateTaskDTO, Task> {

	@Override
	public Task apply(
			CreateTaskDTO t) {
		return new Task(t.getUserId(), t.getName(), t.getDescription());
	}
}
