package br.com.reader.pdf.model;

import java.io.File;

/**
 * Classe que armazena as informacoes para recarregar
 * o PDF para continuar da ultima pagina vista. 
 * 
 * @author Gabriel Barzagli
 */
public class LastReadInfo {
	/** Arquivo a ser carregado. */
	public File file;
	
	/** Pagina a ser renderizada. */
	public int page;
}
