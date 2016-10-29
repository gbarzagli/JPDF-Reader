package br.com.reader.pdf.exception;

public class InexistentPageException extends Exception {
	private static final long serialVersionUID = 8771206551867485227L;

	public InexistentPageException() {
	}

	public InexistentPageException(String message) {
		super(message);
	}

	public InexistentPageException(Throwable cause) {
		super(cause);
	}

	public InexistentPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public InexistentPageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
