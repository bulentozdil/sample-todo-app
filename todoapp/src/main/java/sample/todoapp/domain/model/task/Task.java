package sample.todoapp.domain.model.task;

import java.util.Date;
import java.util.UUID;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Task {

	@Id 
	private String id;
	@Field 
	@QueryIndexed
	private String userId;
	@Field
	private String name;
	@Field
	private String description;
	@Field 
	private boolean isCompleted;
	@Field 
	private boolean isDeleted;
	@Field
	private long timespan;
	
	/**
	 * 
	 */
	public Task() {
	
	}

	/**
	 * @param realmId
	 * @param name
	 * @param description
	 * @param planDate
	 */
	public Task(
			String userId,
			String name,
			String description) {
		this.userId = userId;
		this.name = name;
		this.description = description;

		// Default fields
		this.isCompleted = false;
		this.isCompleted = false;
		
		this.timespan = new Date().getTime();
		
		this.id = UUID.randomUUID().toString();
	}
	
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id=id;
	}

	/**
	 * @param name
	 * @param description
	 */
	public void applyChanges(
			String name,
			String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * 
	 */
	public void setAsCompleted() {
		this.isCompleted = true;
	}

	/**
	 * 
	 */
	public void setAsDeleted() {
		this.isDeleted = true;
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
	public boolean getIsCompleted() {
		return isCompleted;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}
	
	/**
	 * @return the timespan
	 */
	public long getTimespan() {
		return timespan;
	}
}
