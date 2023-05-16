package in.karlfahd.springbootmongodb.service;
//package in.karlfahd.springbootmongodb.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.karlfahd.springbootmongodb.exception.TaskCollectionException;
import in.karlfahd.springbootmongodb.model.Task;
import in.karlfahd.springbootmongodb.repository.TaskRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository todoRepo;
	
	@Override
	public void createTodo(Task task) throws ConstraintViolationException, TaskCollectionException{
		Optional<Task> todoOptional = todoRepo.findByTask(task.getTask());
		if(todoOptional.isPresent()) {
			throw new TaskCollectionException(TaskCollectionException.TaskAlreadyExists());
		}else {
			//task.setCreatedAt(new Date(System.currentTimeMillis()));
			
			todoRepo.save(task);
		}
	}
	
	

}

