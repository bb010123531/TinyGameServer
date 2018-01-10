package tiny.gs.exception;

public class UnCheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnCheckedException() {
	}

	public UnCheckedException(String message) {
		super(message);
	}

	public UnCheckedException(Throwable cause) {
		super(cause);
	}

	public UnCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnCheckedException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
