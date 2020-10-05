package sample.todoapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.todoapp.converter.TaskDTOConverter;
import sample.todoapp.domain.model.task.Task;
import sample.todoapp.dto.CreateTaskDTO;
import sample.todoapp.dto.TaskDTO;
import sample.todoapp.service.TaskService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	@MockBean
	private TaskDTOConverter taskConverter;
	
	private ObjectMapper mapper;
	
	@Before
	public void init() {
		this.mapper=new ObjectMapper();
	}
	
	@Test
	public void it_should_update_as_deleted() throws Exception {
		
		//given
		String taskId="123xyz";
		
		given(taskService.setTaskAsDeleted(taskId)).willReturn(true);
		
		//when
        var result = mockMvc.perform(
        		put("/tasks/"+taskId+"/deleted")
        			.accept(MediaType.APPLICATION_JSON) 
        			.contentType(MediaType.APPLICATION_JSON_VALUE)
        			.characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
        
        // then
        boolean retval = Boolean.valueOf(result.getResponse().getContentAsString());
        assertTrue(retval);
	}
	
	@Test
	public void it_should_update_as_completed() throws Exception {
		
		//given
		String taskId="123xyz";
		
		given(taskService.setTaskAsCompleted(taskId)).willReturn(true);
		
		//when
        var result = mockMvc.perform(
        		put("/tasks/"+taskId+"/completed")
        			.accept(MediaType.APPLICATION_JSON) 
        			.contentType(MediaType.APPLICATION_JSON_VALUE)
        			.characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
         
        // then
        boolean retval = Boolean.valueOf(result.getResponse().getContentAsString());
        assertTrue(retval);
	}
	
	@Test
	public void it_should_edit_task() throws Exception {
		
		//given
		String taskId="123xyz";
		
		var dto = new CreateTaskDTO(taskId,"1", "newest task edit", "description edit");
		var task = new Task("1", "newest task edit", "description edit");
		task.setId(taskId);
		var taskDTO = new TaskDTO(taskId, "1", "newest task edit", "description edit");
		
		given(taskService.edit(any(CreateTaskDTO.class))).willReturn(task);
		given(taskConverter.apply(task)).willReturn(taskDTO);
		
		String body = mapper.writeValueAsString(dto); 
		
		//when
        mockMvc.perform(
        		put("/tasks/"+taskId)
        			.accept(MediaType.APPLICATION_JSON)
        			.content(body)
        			.contentType(MediaType.APPLICATION_JSON_VALUE)
        			.characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("newest task edit"))
                .andExpect(jsonPath("$.description").value("description edit"));
        
        //then
        assertEquals(dto.getName(), task.getName());
        assertEquals(dto.getDescription(), task.getDescription());
	}
	
	@Test
	public void it_should_create_new_task() throws Exception {
		
		// given
		
		String userId = "1";
		
		var dto = new CreateTaskDTO(userId, "newest task", "description");
		var task = new Task(userId, "newest task", "description");
		 
		given(taskService.create(dto)).willReturn(task); 
		 
		String body = mapper.writeValueAsString(dto);
		
		//when
        mockMvc.perform(
        		post("/tasks?userId="+userId)
        			.accept(MediaType.APPLICATION_JSON)
        			.content(body)
        			.contentType(MediaType.APPLICATION_JSON_VALUE)
        			.characterEncoding("UTF-8"))
                .andExpect(status().isOk());
	}
	
	@Test
	public void it_should_get_all_by_userid() throws Exception {
		
		//given
		var tasks = Arrays.asList(
				new Task("ozdilbulent1@gmail.com", "new task 1", "Hello my first task 1"),
				new Task("ozdilbulent2@gmail.com", "new task 2", "Hello my first task 2"),
				new Task("ozdilbulent3@gmail.com", "new task 3", "Hello my first task 3"));
		
		var taskDTOs=Arrays.asList(
				 new TaskDTO("123xyz", "ozdilbulent1@gmail.com", "new task 1", "Hello my first task 1", 12314234),
				 new TaskDTO("123xyy", "ozdilbulent2@gmail.com", "new task 2", "Hello my first task 2", 12314234),
				 new TaskDTO("123xxx", "ozdilbulen3@gmail.com", "new task 3", "Hello my first task 3", 12314234));
		
		String userId = "1";
		
		given(taskService.getAllByUserId(userId)).willReturn(tasks);
		given(taskConverter.apply(tasks.get(0))).willReturn(taskDTOs.get(0));
		
		 //when
        mockMvc.perform(get("/tasks?userId="+userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("ozdilbulent1@gmail.com"))
                .andExpect(jsonPath("$[0].name").value("new task 1"))
                .andExpect(jsonPath("$[0].description").value("Hello my first task 1"));
	}

	@Test
	public void it_should_get_by_id_and_userid() throws Exception {

		// given
		Task task = new Task("ozdilbulent@gmail.com", "new task", "Hello my first task");
		task.setId("123");
		
		given(taskService.getOne("123", "ozdilbulent@gmail.com")).willReturn(task);
		
		TaskDTO dto = new TaskDTO("123", "ozdilbulent@gmail.com", "new task", "Hello my first task", 12314234);
		
		given(taskConverter.apply(task)).willReturn(dto);
		
		 //when
        mockMvc.perform(get("/tasks/123?userId="+"ozdilbulent@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("new task"))
                .andExpect(jsonPath("$.description").value("Hello my first task"));
	}
}
