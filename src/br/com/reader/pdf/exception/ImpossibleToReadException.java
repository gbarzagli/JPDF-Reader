package br.com.reader.pdf.exception;

public class ImpossibleToReadException extends Exception {
	private static final long serialVersionUID = -7460017810406589389L;

	public ImpossibleToReadException() {
	}

	public ImpossibleToReadException(String message) {
		super(message);
	}

	public ImpossibleToReadException(Throwable cause) {
		super(cause);
	}

	public ImpossibleToReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImpossibleToReadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
