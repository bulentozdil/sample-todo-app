package sample.todoapp.exception;

public class ErrorResponse {

	private String code;
	private String message;

	/**
	 * @param ex
	 */
	public ErrorResponse(
			GlobalException ex) {
		this.code = ex.getCode();
		this.message = ex.getMessage();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
