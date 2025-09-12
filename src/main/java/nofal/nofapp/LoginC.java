package nofal.nofapp;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginC {
	private final Gson gson = new Gson();
	private final SaveJson saveJson = new SaveJson();
	private final Alert conAlert = new Alert(Alert.AlertType.CONFIRMATION);
	private final Alert badAlert = new Alert(Alert.AlertType.ERROR);
	@FXML
	private Button Signupbtn, Loginbtn,LoginAcc;
	@FXML
	private TextField Email;
	@FXML
	private PasswordField Password;

	@FXML
	private Label NameLabel,EmailLabel,PasswordLabel;

	@FXML
	public void initialize(){
		Loginbtn.setStyle("-fx-background-color: #3498db;");
	}

	public void Login(){
		if(!saveJson.CheckData(Email, Password)){
			System.out.println("Account was not found");
			Accnotfound();
			return;
		}
		confirmation();
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

	public void SignupSwitch() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
		Parent SignupRoot = loader.load();

		Scene scene = Signupbtn.getScene();
		scene.setRoot(SignupRoot);
	}
}
