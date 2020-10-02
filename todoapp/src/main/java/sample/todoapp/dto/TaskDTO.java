package sample.todoapp.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {

	private String id;
	private String userId;
	private String name;
	private String description;

	@JsonProperty
	private boolean isCompleted;
	@JsonProperty
	private boolean isDeleted;
	private long timespan;
	
	/**
	 * @param id
	 * @param userId
	 * @param name
	 * @param description
	 */
	public TaskDTO(
			String id,
			String userId,
			String name,
			String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	public TaskDTO(
			String id,
			String userId,
			String name,
			String description,
			long timespan) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.timespan = timespan;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @return the timespan
	 */
	public long getTimespan() {
		return timespan;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(
			String id) {
		this.id = id;
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

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setCompleted(
			boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(
			boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @param timespan the timespan to set
	 */
	public void setTimespan(
			long timespan) {
		this.timespan = timespan;
	}
}
