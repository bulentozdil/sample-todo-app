package sample.todoapp.dto;

public class CreateNewUserDTO {

	private String fullname;
	private String email;
	private String password;

	/**
	 * 
	 */
	public CreateNewUserDTO() {

	}

	/**
	 * @param fullname
	 * @param email
	 * @param password
	 */
	public CreateNewUserDTO(
			String fullname,
			String email,
			String password) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
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

	/**
	 * @param password the password to set
	 */
	public void setPassword(
			String password) {
		this.password = password;
	}
}
