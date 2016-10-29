package br.com.reader.pdf.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
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
	 * Cria uma instancia do manipulador de PDF.
	 * @param file arquivo referente ao PDF
	 * @throws InvalidFileException se nao for possivel ler o arquivo.
	 */
	public PDFHandler(File file) throws InvalidFileException {
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
	 * @throws ImpossibleToReadException se nao for possivel ler a pagina
	 */
	public BufferedImage getPDFPageAsImage(int page) throws InexistentPageException, ImpossibleToReadException {
		if(page >= document.getNumberOfPages()) {
			throw new InexistentPageException();
		}
		
		try {
			return renderer.renderImage(page);
		} catch (IOException e) {
			throw new ImpossibleToReadException("Não foi possível ler o arquivo.", e);
		}
	}
	
	

}
