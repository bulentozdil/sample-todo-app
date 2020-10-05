package sample.todoapp.repository;

import java.time.Duration; 

import org.springframework.stereotype.Component;
 
import com.couchbase.client.java.Bucket; 
import com.couchbase.client.java.kv.InsertOptions; 

import sample.todoapp.config.Constants; 
import sample.todoapp.domain.model.user.User; 

@Component
public class UserRepository extends Repository {
 
	public User save(
			User entity) {
		bucket().defaultCollection().insert(entity.getId(), entity, InsertOptions.insertOptions().timeout(Duration.ofSeconds(30))); 
		return entity;
	}
 
	public User findById(
			String id) {
		var result = bucket().defaultCollection().get(id);
		return result.contentAs(User.class); 
	}
	
	@Override
	public Bucket bucket() {
		return cluster().bucket(Constants.BUCKET_USER);
	}
}
