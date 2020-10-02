package sample.todoapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64; 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.domain.model.user.User;

@RunWith(SpringRunner.class)
public class UserTest {

	@Test
	public void it_should_be_password_encoded_base_64() {

		User user = new User("bülent özdil", "ozdilbulent@gmail.com", "123xyz");
		user.encodePassword();

		String passwordBase64 = Base64.getEncoder().encodeToString("123xyz".getBytes());

		assertEquals(passwordBase64, user.getPassword());
	}

	@Test(expected = NullPointerException.class)
	public void it_should_be_password_encoded_base_64_throw_error() {
		User user = new User("bülent özdil", "ozdilbulent@gmail.com", null);
		user.encodePassword(); 
	}
}
