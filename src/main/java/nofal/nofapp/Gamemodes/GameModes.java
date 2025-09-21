package nofal.nofapp.Gamemodes;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nofal.nofapp.AnimationUtil.Animations;
import nofal.nofapp.managers.SceneManager;

public class GameModes {
	private final Animations animations = new Animations();
	@FXML
	private Button PackManBtn,googleBtn;

	public void initialize(){
		animations.BtnGrow(PackManBtn);
		animations.BtnGrow(googleBtn);
	}

	public void StartPacMan() throws IOException {
		SceneManager.openNewWindow("/nofal/nofapp/Pacman.fxml", "PacMan");
	}

	public void openGoogle() throws IOException {
		SceneManager.openNewWindow("/nofal/nofapp/Web.fxml", "Web");
	}
}
