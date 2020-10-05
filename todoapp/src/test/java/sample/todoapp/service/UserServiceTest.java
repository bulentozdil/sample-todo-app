package sample.todoapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner; 

import sample.todoapp.converter.UserConverter;
import sample.todoapp.domain.model.user.User; 
import sample.todoapp.dto.CreateNewUserDTO;
import sample.todoapp.repository.UserRepository; 

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserConverter userConverter;

	@Mock
	private UserRepository userRepository;

	@Test
	public void it_should_be_registered() {

		// given
		CreateNewUserDTO dto = new CreateNewUserDTO("bülent özdil", "ozdilbulent@gmail.com", "123456");
		var user = new User("bülent özdil", "ozdilbulent@gmail.com", "123456");

		Mockito.when(userConverter.apply(dto)).thenReturn(user);
		
		// when
		var createdUser = userService.createNewUser(dto);

		// then
		assertEquals(createdUser, user);
		Mockito.verify(userRepository).save(user);
	}
}
