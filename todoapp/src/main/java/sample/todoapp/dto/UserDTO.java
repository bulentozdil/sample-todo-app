package sample.todoapp.dto;

import org.springframework.core.style.ToStringCreator;

public class UserDTO {

	private String fullname;
	private String email;

	/**
	 * @param fullname
	 * @param email
	 */
	public UserDTO(
			String fullname,
			String email) {
		this.fullname = fullname;
		this.email = email;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(
			String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @param email the email to set
	 */
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
