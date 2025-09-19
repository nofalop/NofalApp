package nofal.nofapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneManager {

	public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
		Parent root = loader.load();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene newScene = new Scene(root); // creates fresh scene with FXML’s preferred size
		stage.setScene(newScene);
		stage.centerOnScreen();
		stage.show();
	}
	public static void openNewWindow(String fxmlFile, String stageName) throws IOException {
		FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
		Parent root = loader.load();

		Stage stage = new Stage();

		stage.setTitle(stageName);
		stage.setScene(new Scene(root));
		stage.show();
	}
	public static <T> T openNewWindowGetC(String fxmlFile, String stageName) throws IOException {
		URL fxmlUrl = SceneManager.class.getResource(fxmlFile);
		if (fxmlUrl == null) {
			throw new IllegalStateException("FXML not found on classpath: " + fxmlFile);
		}

		FXMLLoader loader = new FXMLLoader(fxmlUrl);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle(stageName);
		stage.setScene(new Scene(root));
		stage.show();

		return loader.getController();
	}
}
