package sample.todoapp.dto;

public class CreateTaskDTO {

	private String taskId;
	private String userId;
	private String name;
	private String description;

	public CreateTaskDTO() {

	}

	public CreateTaskDTO(
			String userId,
			String name,
			String description) {
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	public CreateTaskDTO(
			String taskId,
			String userId,
			String name,
			String description) {
		super();
		this.taskId = taskId;
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setTaskId(
			String taskId) {
		this.taskId = taskId;
	}

	public void setUserId(
			String userId) {
		this.userId = userId;
	}

	public void setName(
			String name) {
		this.name = name;
	}

	public void setDescription(
			String description) {
		this.description = description;
	}
}
