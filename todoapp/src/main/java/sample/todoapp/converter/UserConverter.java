package sample.todoapp.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import sample.todoapp.domain.model.user.User;
import sample.todoapp.dto.CreateNewUserDTO;

@Component
public class UserConverter implements Function<CreateNewUserDTO, User> {

	@Override
	public User apply(
			CreateNewUserDTO t) {
		
		return new User(t.getFullname(), t.getEmail(), t.getPassword());
	}
}
