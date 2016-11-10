package br.com.reader.pdf.Application;

import java.awt.image.BufferedImage;
import java.io.File;
import br.com.reader.pdf.controller.MainPageController;
import br.com.reader.pdf.exception.ImpossibleToReadException;
import br.com.reader.pdf.exception.InexistentPageException;
import br.com.reader.pdf.exception.InvalidFileException;
import br.com.reader.pdf.model.PDFHandler;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Test extends Application{

	public static void main(String[] args) throws InvalidFileException, InexistentPageException, ImpossibleToReadException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/br/com/reader/pdf/view/MainPage.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("PDF Reader");
		primaryStage.setMaximized(true);
		
		primaryStage.show();
		
	}

}
