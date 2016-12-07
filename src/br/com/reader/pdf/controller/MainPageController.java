package br.com.reader.pdf.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.reader.pdf.exception.ImpossibleToReadException;
import br.com.reader.pdf.exception.InexistentPageException;
import br.com.reader.pdf.exception.InvalidFileException;
import br.com.reader.pdf.model.FileUtil;
import br.com.reader.pdf.model.LastReadInfo;
import br.com.reader.pdf.model.PDFHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * Classe que manipula a tela.
 * 
 * @author Fernando Soldera
 *
 */
public class MainPageController implements Initializable {

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
	@FXML
	private MenuItem menuItemSearch;

	/**
	 * Objeto manipulador do pdf.
	 */
	private PDFHandler pdf = null;

	/**
	 * Objeto da imagem bufferizada.
	 */
	private BufferedImage imageBuffered = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Carregando as imagens nos botões.
		Image imageButtonNext = new Image("/images/next.png");
		Image imageButtonPrevious = new Image("/images/back.png");

		// Pegando o tamanho da tela.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		// Setando o tamanho e posição do HBox.
		hBox.setPrefWidth(screen.getWidth());
		hBox.setPrefHeight(screen.getHeight());
		hBox.setAlignment(Pos.CENTER);

		// Setando o tamanho dos botoes e a menuBar de acordo com a tela.
		menuBar.setPrefWidth(screen.getWidth());
		buttonNext.setLayoutX(screen.getWidth() - 49);
		buttonNext.setLayoutY(screen.getHeight() / 2);
		buttonPrevious.setLayoutX(0);
		buttonPrevious.setLayoutY(screen.getHeight() / 2);

		// Colocando as imagens nos botões.
		buttonNext.setGraphic(new ImageView(imageButtonNext));
		buttonPrevious.setGraphic(new ImageView(imageButtonPrevious));

		try {
			LastReadInfo lastReadInfo = FileUtil.reloadLastRead();
			File file = lastReadInfo.file;
			int page = lastReadInfo.page;
			
			
			buildPDFHandler(file);
			showImage(page);
		} catch (IOException e) {
		}
	}

	/**
	 * mostra a imagem na imageView.
	 * 
	 * @param page
	 *            valor referente a pagina que sera mostrada.
	 */
	private void showImage(int page) {
		if (pdf != null) {
			try {
				imageBuffered = pdf.getPDFPageAsImage(page);
				
				Image image = SwingFXUtils.toFXImage(imageBuffered, null);
				
				imageView.setFitHeight(image.getHeight());
				imageView.setFitWidth(image.getWidth());
		
				imageView.setImage(image);
			} catch (InexistentPageException e) {
				if (e.isEndOfFile()) {
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("");
					dialog.setHeaderText("Você chegou ao fim do arquivo.");
					dialog.setContentText("Não há mais páginas a serem visualizadas.");
					dialog.showAndWait();
				} else {
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("");
					dialog.setHeaderText("Você chegou ao inicio do arquivo.");
					dialog.setContentText("Não há mais páginas anteriores.");
					dialog.showAndWait();
				}
			} catch (ImpossibleToReadException e) {
				Alert dialog = new Alert(Alert.AlertType.ERROR);
				dialog.setTitle("Arquivo não pode ser lido");
				dialog.setHeaderText("O arquivo não pôde ser lido.");
				dialog.setContentText(
						"O arquivo selecionado não pôde ser lido.\nIsso pode ter acontecido por o arquivo estar corrompido.");
				dialog.showAndWait();
			}
		}
	}
	
	private void buildPDFHandler(File file) {
		try {
			pdf = new PDFHandler(file);
		} catch (InvalidFileException e) {
			Alert dialog = new Alert(Alert.AlertType.ERROR);
			dialog.setTitle("Arquivo inválido");
			dialog.setHeaderText("O arquivo selecionado não é um PDF");
			dialog.setContentText("Este programa só lê arquivos PDF.\nPor favor, selecione um arquivo do tipo PDF.");
			dialog.showAndWait();
		}
	}

	@FXML
	public void handleButtonNext() {
		int page = pdf.getNextPage();
		showImage(page);
	}

	@FXML
	public void handleButtonPrevious() {
		int page = pdf.getPreviousPage();
		showImage(page);
	}

	@FXML
	public void handleButtonMenuItemSearch() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		
		if (file != null) {
			buildPDFHandler(file);
			int page = pdf.getActualPage();
			showImage(page);
		}
	}
}