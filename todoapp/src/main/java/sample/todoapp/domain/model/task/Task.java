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
	private long timespan;
	
	public Task() {
	
	}

	public Task(
			String userId,
			String name,
			String description) {
		this.userId = userId;
		this.name = name;
		this.description = description;

		// Default fields
		this.isCompleted = false;
		
		this.timespan = new Date().getTime();
		
		this.id = UUID.randomUUID().toString();
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public void applyChanges(
			String name,
			String description) {
		this.name = name;
		this.description = description;
	}

	public void setAsCompleted() {
		this.isCompleted = true;
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

	public boolean getIsCompleted() {
		return isCompleted;
	}
	
	public long getTimespan() {
		return timespan;
	}
}
