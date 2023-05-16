package in.karlfahd.springbootmongodb.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.karlfahd.springbootmongodb.exception.TaskCollectionException;
import in.karlfahd.springbootmongodb.model.Task;
import in.karlfahd.springbootmongodb.repository.TaskRepository;
import in.karlfahd.springbootmongodb.service.TaskService;
import jakarta.validation.ConstraintViolationException;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository todoRepo;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public ResponseEntity<?> getAllTasks(){
		List<Task> tasks = todoRepo.findAll();
		if(tasks.size()>0) {
			return new ResponseEntity<List<Task>>(tasks,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No tasks available",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<?> createTask(@RequestBody Task task){
		try {
		//	task.setCreatedAt(new Date(System.currentTimeMillis()));  //hol tnen shilon
		//	todoRepo.save(task);
			taskService.createTodo(task);
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		}catch(ConstraintViolationException e) {
		//TODO: handle exception	
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(TaskCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<?> getSingleTask(@PathVariable("id")String id){
		Optional<Task> taskOptional =todoRepo.findById(id);
		if(taskOptional.isPresent()) {
			return new ResponseEntity<>(taskOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Task not found with id" +id, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/tasks/DueDate")
    public ResponseEntity<?> getTasksWithLessThanThreeDaysDue() {
        List<Task> tasks = todoRepo.findAll();
        List<Task> tasksWithLessThanThreeDaysDue = new ArrayList<>();

        Date currentDate = new Date();
        long threeDaysInMillis = 3 * 24 * 60 * 60 * 1000; // Convert 3 days to milliseconds

        for (Task task : tasks) {
            if (task.getDueDate() != null) {
                long timeDifference = task.getDueDate().getTime() - currentDate.getTime();
                if (timeDifference < threeDaysInMillis && task.getStatus()!="completed") {
                    tasksWithLessThanThreeDaysDue.add(task);
                }
            }
        }

        if (!tasksWithLessThanThreeDaysDue.isEmpty()) {
            return new ResponseEntity<>(tasksWithLessThanThreeDaysDue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No tasks with less than three days due", HttpStatus.NOT_FOUND);
        }
    }
	

	@PutMapping("/tasks/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id")String id, @RequestBody Task task){
		Optional<Task> taskOptional = todoRepo.findById(id);
		if(taskOptional.isPresent()) {
			Task taskToSave = taskOptional.get();
			taskToSave.setStatus(task.getStatus()!=null ? task.getStatus(): taskToSave.getStatus());
			taskToSave.setTask(task.getTask()!=null ? task.getTask(): taskToSave.getTask());
			taskToSave.setDescription(task.getDescription()!=null ? task.getDescription() : taskToSave.getDescription());
			taskToSave.setDueDate(task.getDueDate()!=null ? task.getDueDate(): taskToSave.getDueDate());
			todoRepo.save(taskToSave);
			return new ResponseEntity<>(taskToSave, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Task not found with id" +id, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")String id){
		try {
			todoRepo.deleteById(id);
			return new ResponseEntity<>("Successfully deleted with id" +id, HttpStatus.OK);
		}catch(Exception e) {
		//TODO: handle exception	
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}	
		
	}
	
		
}
