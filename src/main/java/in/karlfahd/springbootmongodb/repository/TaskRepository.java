package in.karlfahd.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.karlfahd.springbootmongodb.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	//?0 index
	@Query("{'task': ?0}")
	Optional<Task>findByTask(String task);
	
}
