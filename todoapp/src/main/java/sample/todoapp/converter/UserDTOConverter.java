package sample.todoapp.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import sample.todoapp.domain.model.user.User;
import sample.todoapp.dto.UserDTO;

@Component
public class UserDTOConverter implements Function<User, UserDTO> {

	@Override
	public UserDTO apply(
			User t) {
		return new UserDTO(t.getFullname(),t.getEmail());
	}
}
