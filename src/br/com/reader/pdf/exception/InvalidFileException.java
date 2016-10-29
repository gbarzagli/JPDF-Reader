package br.com.reader.pdf.exception;

public class InvalidFileException extends Exception {
	private static final long serialVersionUID = -7190126487705732319L;

	public InvalidFileException() {
	}

	public InvalidFileException(String message) {
		super(message);
	}

	public InvalidFileException(Throwable cause) {
		super(cause);
	}

	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
