package br.com.reader.pdf.exception;

public class InexistentPageException extends Exception {
	private static final long serialVersionUID = 8771206551867485227L;
	
	boolean isEndOfFile;

	/**
	 * Excecao chamada quando a pagina nao existe
	 * @param eof se é o fim do arquivo ou não.
	 */
	public InexistentPageException(boolean eof) {
		this.isEndOfFile = eof;
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
	
	/**
	 * Retorna se é o fim do arquivo.
	 * @return true se for o fim do arquivo ou false se for o inicio.
	 */
	public boolean isEndOfFile() {
		return isEndOfFile;
	}

}
