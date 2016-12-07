package br.com.reader.pdf.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import br.com.reader.pdf.exception.ImpossibleToReadException;
import br.com.reader.pdf.exception.InexistentPageException;
import br.com.reader.pdf.exception.InvalidFileException;

/**
 * Classe manipuladora de PDF
 * @author Gabriel Barzagli
 */
public class PDFHandler {
	
	/**
	 * Objeto que representa o PDF
	 */
	private PDDocument document;
	
	/**
	 * Objeto que renderiza as paginas do PDF
	 */
	private PDFRenderer renderer;
	
	/**
	 * Objeto que armazena o arquivo PDF
	 */
	private File file;
	
	/**
	 * Variável que armazena a pagina que está.
	 */
	private int page;
	
	/**
	 * Cria uma instancia do manipulador de PDF.
	 * @param file arquivo referente ao PDF
	 * @throws InvalidFileException se nao for possivel ler o arquivo.
	 */
	public PDFHandler(File file) throws InvalidFileException {
		this.file = file;
		this.page = 0;
		load();
	}
	
	/**
	 * Carrega o arquivo.
	 * @throws InvalidFileException se nao for possivel ler o arquivo.
	 */
	private void load() throws InvalidFileException {
		try {
			this.document = PDDocument.load(file);
			this.renderer = new PDFRenderer(document);
		} catch (IOException e) {
			throw new InvalidFileException("Tipo de arquivo inválido.", e);
		}
	}
	
	/**
	 * Pega a pagina do PDF renderizada como uma imagem.
	 * @param page numero da pagina (0 - n)
	 * @return BufferedImage imagem bufferizada para ser exibida.
	 * @throws InexistentPageException se a pagina requisitada nao existe.
	 * @throws ImpossibleToReadException se nao for possivel ler a pagina.
	 * @throws InvalidFileException 
	 */
	public BufferedImage getPDFPageAsImage(int page) throws InexistentPageException, ImpossibleToReadException {
		try {
			if (document == null) {
				load();
			}
			
			if(page >= getNumberOfPages()) {
				this.page = getNumberOfPages() - 1;
				throw new InexistentPageException(true);
			} else if(page < 0) {
				this.page = 0;
				throw new InexistentPageException(false);
			}
			
			FileUtil.save(file, page);
			return renderer.renderImage(page);
		} catch (IOException e) {
			throw new ImpossibleToReadException("Não foi possível ler o arquivo.", e);
		} catch (InvalidFileException e) {
			throw new ImpossibleToReadException("Não foi possível ler o arquivo.", e);
		} finally {
			try {
				this.document.close();
				this.document = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * Retorna a proxima pagina
	 * @return retorna a proxima pagina
	 */
	public int getNextPage() {
		if (page < getNumberOfPages()) {
			page++;
		}
		return page;
	}
	
	/**
	 * Retorna a pagina atual.
	 * @return retorna a pagina atual
	 */
	public int getActualPage() {
		return page;
	}
	
	/**
	 * Retorna uma pagina.
	 * @return retorna a pagina anterior
	 */
	public int getPreviousPage() {
		if (page >= 0) {
			page--;
		}
		return page;
	}
	
	/**
	 * Pega a quantidade de paginas do pdf
	 * @return o numero de paginas do pdf
	 */
	public int getNumberOfPages(){
		if (document == null) {
			try { 
				load();
			} catch (InvalidFileException e) {
				return -1;
			}
		}
		
		return document.getNumberOfPages();
	}
	
}
