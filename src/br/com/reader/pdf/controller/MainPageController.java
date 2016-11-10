package br.com.reader.pdf.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.reader.pdf.exception.ImpossibleToReadException;
import br.com.reader.pdf.exception.InexistentPageException;
import br.com.reader.pdf.exception.InvalidFileException;
import br.com.reader.pdf.model.PDFHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainPageController implements Initializable{

	@FXML
	private AnchorPane anchorpane;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Button buttonPrevious;
	@FXML
	private Button buttonNext;
	@FXML
	private ImageView imageView;
	@FXML
	private HBox hBox;
	
	/**
	 * Objeto manipulador do pdf
	 */
	private PDFHandler pdf = null;
	
	/**
	 * Objeto da imagem bufferizada
	 */
	private BufferedImage imageBuffered = null;
	
	/**
	 * Objeto que armazena o caminho ate o arquivo PDF
	 */
	private String pathname; //"C:/Users/IFSP - Campinas/Desktop/Faculdade/intlat.pdf"
	
	private int page;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image imageButtonNext = new Image("/images/next.png");
		Image imageButtonPrevious = new Image("/images/back.png");
		
		//Pegando o tamanho da tela
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		hBox.setPrefWidth(screen.getWidth());
		hBox.setPrefHeight(screen.getHeight());
		hBox.setAlignment(Pos.CENTER);
		
		//Setando o tamanho dos botoes e a menuBar de acordo com a tela
		menuBar.setPrefWidth(screen.getWidth());
		buttonNext.setLayoutX(screen.getWidth() - 49);
		buttonNext.setLayoutY(screen.getHeight()/2);
		buttonPrevious.setLayoutX(0);
		buttonPrevious.setLayoutY(screen.getHeight()/2);
	
		//Colocando as imagens nos botões
		buttonNext.setGraphic(new ImageView(imageButtonNext));
		buttonPrevious.setGraphic(new ImageView(imageButtonPrevious));
		
		pathname = "C:/Users/IFSP - Campinas/Desktop/Faculdade/intlat.pdf";
		page = 0;
		
		showImage();
	}
	
	/**
	 * mostra a imagem na imageView.
	 * @param page valor referente a pagina que sera mostrada.
	 */
	public void showImage(){
		try {
			pdf = new PDFHandler(new File(pathname));
		} catch (InvalidFileException e) {
			e.printStackTrace();
		}
		
		try {
			imageBuffered = pdf.getPDFPageAsImage(page);
		} catch (InexistentPageException e) {
			e.printStackTrace();
		} catch (ImpossibleToReadException e) {
			e.printStackTrace();
		}
		
		Image image = SwingFXUtils.toFXImage(imageBuffered, null);
		
		imageView.setFitHeight(image.getHeight());
		imageView.setFitWidth(image.getWidth());
		
		imageView.setImage(image);
	}
	
	@FXML
	public void handleButtonNext(){
		page++;
		showImage();
	}
	
	@FXML
	public void handleButtonPrevious(){
		page--;
		showImage();
	}
}