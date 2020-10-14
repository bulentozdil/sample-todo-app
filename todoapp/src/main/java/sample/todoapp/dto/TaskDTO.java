package sample.todoapp.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {

	private String id;
	private String userId;
	private String name;
	private String description;

	@JsonProperty
	private boolean isCompleted;
	private long timespan;

	public TaskDTO(String id, String userId, String name, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	public TaskDTO(String id, String userId, String name, String description, long timespan, boolean isCompleted) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.timespan = timespan;
		this.isCompleted = isCompleted;
	}

	public String getId() {
		return id;
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

	public boolean isCompleted() {
		return isCompleted;
	}

	public long getTimespan() {
		return timespan;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setTimespan(long timespan) {
		this.timespan = timespan;
	}
}
