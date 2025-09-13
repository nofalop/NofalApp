package nofal.nofapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nofal.nofapp.Gamemodes.Web;

import java.io.IOException;

public class GameModes {

	@FXML
	private Button PackManBtn,googleBtn;


	public void StartPacMan(){

	}

	public void openGoogle(ActionEvent event) throws IOException {
		SceneManager.switchScene(event, "Web.fxml");
	}
}
