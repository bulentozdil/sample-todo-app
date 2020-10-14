package sample.todoapp.converter;

import java.util.function.Function;
import org.springframework.stereotype.Component;

import sample.todoapp.domain.model.task.Task;
import sample.todoapp.dto.TaskDTO;

@Component
public class TaskDTOConverter implements Function<Task, TaskDTO> {

	@Override
	public TaskDTO apply(
			Task t) {
		return new TaskDTO(
				t.getId(), 
				t.getUserId(), 
				t.getName(), 
				t.getDescription(), 
				t.getTimespan(),
				t.getIsCompleted());
	}
}
