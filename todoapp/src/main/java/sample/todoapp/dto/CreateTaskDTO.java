package sample.todoapp.dto;

public class CreateTaskDTO {

	private String taskId;
	private String userId;
	private String name;
	private String description;

	public CreateTaskDTO() {

	}

	/**
	 * @param userId
	 * @param name
	 * @param description
	 */
	public CreateTaskDTO(
			String userId,
			String name,
			String description) {
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	/**
	 * @param taskId
	 * @param userId
	 * @param name
	 * @param description
	 */
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

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(
			String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(
			String userId) {
		this.userId = userId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(
			String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(
			String description) {
		this.description = description;
	}
}
