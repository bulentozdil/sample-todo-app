package sample.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.todoapp.converter.UserDTOConverter;
import sample.todoapp.dto.CreateNewUserDTO;
import sample.todoapp.dto.UserDTO;
import sample.todoapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDTOConverter converter;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> create(
			@RequestBody CreateNewUserDTO dto) { 
		return ResponseEntity.ok(converter.apply(userService.createNewUser(dto)));
	}
}
