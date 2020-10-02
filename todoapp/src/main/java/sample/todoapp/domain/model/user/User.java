package sample.todoapp.domain.model.user;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class User {

	@Id
	private String email;

	@Field
	private String fullname;

	@Field
	private String password;

	/**
	 * @param fullname
	 * @param email
	 * @param password
	 */
	public User(
			String fullname,
			String email,
			String password) {
		checkIfNullOrEmpty(email, "The email is required");
		checkIfNullOrEmpty(fullname, "The fullname is required");
		checkIfNullOrEmpty(password, "The password is required");

		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	/**
	 * @param value
	 * @param field
	 */
	private void checkIfNullOrEmpty(
			String value,
			String message) {
		Optional.ofNullable(value)
			.filter(Predicate.not(String::isEmpty).negate().and(f -> f != null).negate())
			.ifPresentOrElse((result) -> {},
				() -> {
					throw new NullPointerException(message);
				});
	}

	/**
	 * @return
	 */
	public void encodePassword() {
		this.password = java.util.Base64.getEncoder().encodeToString(password.getBytes());
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

	@Override
	public boolean equals(
			Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}

		var obj2 = (User) obj;
		return Objects.equals(obj, obj2);
	}

	@Override
	public int hashCode() {
		return this.email.hashCode() + this.fullname.hashCode() + this.password.hashCode();
	}
}
