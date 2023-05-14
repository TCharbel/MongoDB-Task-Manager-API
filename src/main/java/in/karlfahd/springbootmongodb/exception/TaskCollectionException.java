package in.karlfahd.springbootmongodb.exception;

public class TaskCollectionException extends Exception {

	/**
	 * */
	private static final long serialVersionUID = 1L;
	
	public TaskCollectionException(String message ){
		super(message);
	}

	public static String NotFoundException(String id) {
		return "Task WITH " +id +"NOT FOUND";
	}
	
	public static String TaskAlreadyExists() {
		return "Task With given name already exists";
	}
}
