package sample.todoapp.dto;

import org.springframework.core.style.ToStringCreator;

public class UserDTO {

	private String id;
	private String fullname;
	private String email;

	public UserDTO(
			String id,
			String fullname,
			String email) {
		this.id=id;
		this.fullname = fullname;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}


	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setFullname(
			String fullname) {
		this.fullname = fullname;
	}

	public void setEmail(
			String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("fullname",fullname)
				.append("email",email)
				.toString();
	}
}
