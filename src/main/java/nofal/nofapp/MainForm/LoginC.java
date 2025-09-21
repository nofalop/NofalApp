package nofal.nofapp.MainForm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import nofal.nofapp.AnimationUtil.Animations;
import nofal.nofapp.managers.JsonManager;
import nofal.nofapp.managers.SceneManager;

import java.io.IOException;

public class LoginC {
	private final JsonManager jsonManager = new JsonManager();
	private final Alert conAlert = new Alert(Alert.AlertType.CONFIRMATION);
	private final Alert badAlert = new Alert(Alert.AlertType.ERROR);
	private final Animations animations = new Animations();

	@FXML
	private Button Loginbtn,Signupbtn,LoginAcc;
	@FXML
	private TextField Email;
	@FXML
	private PasswordField Password;

	@FXML
	private AnchorPane ACPBg;

	@FXML
	public void initialize(){

		Loginbtn.setStyle("-fx-background-color: #3498db;");
		animations.BtnGrow(Loginbtn);
		animations.BtnGrow(Signupbtn);
		animations.BtnGrow(LoginAcc);
		animations.BgMove(ACPBg);
	}

	public void Login(ActionEvent event) throws IOException {
		if(!jsonManager.CheckData(Email, Password)){
			System.out.println("Account was not found");
			Accnotfound();
			return;
		}
		confirmation();
		SceneManager.switchScene(event, "/nofal/nofapp/GameSelection.fxml");
	}

	private void confirmation(){
		conAlert.setTitle("Login confirmation");
		conAlert.setHeaderText("Logged in!");
		conAlert.setContentText("You have Been logged in using " + Email.getText());
		conAlert.showAndWait();
	}
	private void Accnotfound(){
		badAlert.setTitle("Error");
		badAlert.setHeaderText("Account not found");
		badAlert.setContentText("Account was not found Sadly 😢");
		badAlert.showAndWait();
	}

	public void SignupSwitch(ActionEvent event) throws IOException {
		SceneManager.switchScene(event, "/nofal/nofapp/Signup.fxml");
	}
}
