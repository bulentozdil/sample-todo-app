package sample.todoapp.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sample.todoapp.domain.model.user.User;

@RunWith(SpringRunner.class)
public class UserDTOConverterTest {

	@Test
	public void it_should_expect_converting_user_to_user_dto() {
		
		var converter = new UserDTOConverter();
		var dto = converter.apply(new User("veli", "veli@mail.com", "123xyz"));
		
		assertEquals("veli", dto.getFullname());
		assertEquals("veli@mail.com", dto.getEmail()); 
	}
}
