package sample.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.todoapp.converter.UserConverter;
import sample.todoapp.domain.model.user.User;
import sample.todoapp.domain.model.user.UserRepository; 
import sample.todoapp.dto.CreateNewUserDTO;

@Service
public class UserService {

	private UserRepository userRepository;
	private UserConverter userConverter; 

	/**
	 * @param userRepository
	 * @param userConverter
	 */
	@Autowired
	public UserService(
			UserRepository userRepository,
			UserConverter userConverter) {
		this.userRepository = userRepository;
		this.userConverter = userConverter;
	}

	/**
	 * @param dto
	 */
	public User createNewUser(
			CreateNewUserDTO dto) {
		var user = userConverter.apply(dto);
		user.encodePassword();
		userRepository.save(user);
		return user;
	}
}
