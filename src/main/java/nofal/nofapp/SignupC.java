package nofal.nofapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupC {

	@FXML
	private PasswordField Password;

	@FXML
	private Button Signupbtn, Loginbtn;

	@FXML
	private TextField Name,Email;

	@FXML
	private ComboBox<String> Genders;

	@FXML
	public void initialize(){
		Genders.getItems().addAll("Male", "Female");
		Signupbtn.setStyle("-fx-background-color: #3498db;");
	}

	public void LoginSwitch() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent LoginRoot = loader.load();

		Scene scene = Loginbtn.getScene();
		scene.setRoot(LoginRoot);
	}

	public void Signup(){

	}
}
