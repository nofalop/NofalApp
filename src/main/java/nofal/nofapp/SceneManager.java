package nofal.nofapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
}
