package sample.todoapp.dto;

public class CreateNewUserDTO {

	private String fullname;
	private String email;
	private String password;

	
	public CreateNewUserDTO() {

	}

	public CreateNewUserDTO(
			String fullname,
			String email,
			String password) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setFullname(
			String fullname) {
		this.fullname = fullname;
	}

	public void setEmail(
			String email) {
		this.email = email;
	}

	public void setPassword(
			String password) {
		this.password = password;
	}
}
