package sample.todoapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith; 

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc; 

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.todoapp.converter.UserDTOConverter;
import sample.todoapp.domain.model.user.User;
import sample.todoapp.dto.CreateNewUserDTO;
import sample.todoapp.dto.UserDTO;
import sample.todoapp.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserDTOConverter converter; 
	
	private ObjectMapper mapper; 
	
	@Before
	public void init() {
		mapper = new ObjectMapper();  
	}

	@Test
	public void it_should_be_create_new_user() throws Exception {

		// given
		var dto = new CreateNewUserDTO("bülentözdil", "ozdilbulent@gmail.com", "123456");
		var user = new User("bülentözdil", "ozdilbulent@gmail.com", "123456"); 
		var userDto = new UserDTO("bülentözdil", "ozdilbulent@gmail.com");
		
		given(userService.createNewUser(any(CreateNewUserDTO.class))).willReturn(user);
		
		given(converter.apply(user)).willReturn(userDto);
		 
		String body = mapper.writeValueAsString(dto); 
		 
		 //when
        mockMvc.perform(
        		post("/users")
        			.accept(MediaType.APPLICATION_JSON)
        			.content(body)
        			.contentType(MediaType.APPLICATION_JSON_VALUE)
        			.characterEncoding("UTF-8"))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.fullname").value("bülentözdil"))
                .andExpect(jsonPath("$.email").value("ozdilbulent@gmail.com"));
        
        // then 
        assertEquals(userDto.getFullname(),user.getFullname());
        assertEquals(userDto.getEmail(), user.getEmail());
        
        user.encodePassword();
        String encodedPassword = java.util.Base64.getEncoder().encodeToString(dto.getPassword().getBytes());
        
        assertEquals(encodedPassword, user.getPassword());
	}
}
