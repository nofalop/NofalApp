package nofal.nofapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginC {
	@FXML
	private Button SignUpbtn, Loginbtn,LoginAcc;
	@FXML
	private TextField Email;
	@FXML
	private PasswordField Password;

	@FXML
	public void initialize(){
		Loginbtn.setStyle("-fx-background-color: #3498db;");
	}

	public void SignupSwitch() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
		Parent SignupRoot = loader.load();

		Scene scene = SignUpbtn.getScene();
		scene.setRoot(SignupRoot);
	}
}
