package sample.todoapp.exception;

public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883734095202719747L;

	/**
	 * 
	 */
	private String code;

	/**
	 * @param message
	 * @param code
	 */
	public GlobalException(
			String message,
			String code) {
		super(message);
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
