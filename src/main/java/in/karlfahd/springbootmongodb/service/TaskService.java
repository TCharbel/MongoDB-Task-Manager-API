package in.karlfahd.springbootmongodb.service;
//package in.karlfahd.springbootmongodb.service;


import in.karlfahd.springbootmongodb.exception.TaskCollectionException;
import in.karlfahd.springbootmongodb.model.Task;
import jakarta.validation.ConstraintViolationException;

public interface TaskService {

	public void createTodo(Task task) throws ConstraintViolationException, TaskCollectionException ;
	
	
}
