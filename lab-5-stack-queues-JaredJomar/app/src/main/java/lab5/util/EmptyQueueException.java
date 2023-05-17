package lab5.util;

public class EmptyQueueException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmptyQueueException(String s) {
		super(s);
	}
	
	public EmptyQueueException() {
		super();
	}

}
