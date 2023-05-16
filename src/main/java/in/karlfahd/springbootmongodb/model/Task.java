package in.karlfahd.springbootmongodb.model;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Validated
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="tasks")
public class Task {

	@Id
	private String id;
	
	@NotNull(message ="task cannot be null")
	private String task;
	
	@NotNull(message ="description cannot be null")
	private String description;
	
	@Pattern(regexp = "^(completed|not completed)$", message = "status must be 'completed' or 'not completed'")
	@NotNull(message ="status cannot be null")
	private String status;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@NotNull(message ="dueDate cannot be null")
	 private Date dueDate;

	    public Date getDueDate() {
	        return dueDate;
	    }

	    public void setDueDate(Date dueDate) {
	        this.dueDate = dueDate;
	    }
	    //YY-MM-DDTH
}
