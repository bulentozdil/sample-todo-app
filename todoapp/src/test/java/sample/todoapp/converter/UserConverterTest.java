package sample.todoapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.dto.CreateNewUserDTO;

@RunWith(SpringRunner.class)
public class UserConverterTest {

	@Test
	public void it_should_expect_converting_create_new_user_dto_to_user() {
		
		
		var converter = new UserConverter();
		var user = converter.apply(new CreateNewUserDTO("ahmet cengiz", "ahmetcengiz@mail.com", "123xyz"));
		
		assertEquals("ahmet cengiz", user.getFullname());
		assertEquals("ahmetcengiz@mail.com"	, user.getEmail());
		assertEquals("123xyz", user.getPassword());
	}
}
