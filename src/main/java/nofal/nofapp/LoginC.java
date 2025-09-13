package nofal.nofapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginC {
	private final SaveJson saveJson = new SaveJson();
	private final Alert conAlert = new Alert(Alert.AlertType.CONFIRMATION);
	private final Alert badAlert = new Alert(Alert.AlertType.ERROR);
	@FXML
	private Button Loginbtn;
	@FXML
	private TextField Email;
	@FXML
	private PasswordField Password;

	@FXML
	public void initialize(){
		Loginbtn.setStyle("-fx-background-color: #3498db;");
	}

	public void Login(ActionEvent event) throws IOException {
		if(!saveJson.CheckData(Email, Password)){
			System.out.println("Account was not found");
			Accnotfound();
			return;
		}
		confirmation();
		SceneManager.switchScene(event, "GameSelection.fxml");
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
		SceneManager.switchScene(event, "Signup.fxml");
	}
}
