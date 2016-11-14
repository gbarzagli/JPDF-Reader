package br.com.reader.pdf.application;

import br.com.reader.pdf.exception.ImpossibleToReadException;
import br.com.reader.pdf.exception.InexistentPageException;
import br.com.reader.pdf.exception.InvalidFileException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * classe de execução do projeto.
 * @author Fernando Soldera
 *
 */

public class Main extends Application{

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