package sample.todoapp.repository;

import static org.junit.Assert.assertNotNull; 

import javax.annotation.PostConstruct;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.todoapp.config.Constants;
import sample.todoapp.config.CouchbaseConfig;
import sample.todoapp.domain.model.user.User;
import sample.todoapp.domain.model.user.UserRepository; 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest 
public class UserRepositoryTest {

	private static CouchbaseContainerStarter starter;

	@Autowired
	private UserRepository userRepository;  

	@AfterClass
	public static void after() {
		starter.stop();
	}

	@Test
	public void it_should_be_save() {

		User user = new User("bülent özdil", "bulent@gmail.com", "123456");
		user.encodePassword();
		
		userRepository.save(user);
		
		var createdUser = userRepository.findById(user.getEmail());
		
		assertNotNull(createdUser);
	}

	@TestConfiguration
	static class TestContainerConfig {

		@Autowired
		private CouchbaseConfig config;
		 
		@PostConstruct
		public void init() {
			starter = new CouchbaseContainerStarter();
			starter.start(Constants.BUCKET_USER, 100);
			starter.replaceConfigProperties(config);
		}
	}
}
