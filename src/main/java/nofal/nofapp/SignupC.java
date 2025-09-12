package nofal.nofapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class SignupC {

	private String _Name,_Password,_Email, _Gender;
	private final  SaveJson saveJson = new SaveJson();

	public SignupC(){

	}

	public SignupC(String _Name, String _Password, String _Email, String _Gender){
		this._Name = _Name;
		this._Email = _Email;
		this._Password = _Password;
		this._Gender = _Gender;
	}

	@FXML
	private PasswordField Password;

	@FXML
	private TextField Name,Email;

	@FXML
	private Label NameLabel,EmailLabel,PasswordLabel;

	@FXML
	private Button Signupbtn, Loginbtn,SignupAcc;


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

	private boolean CheckIfValid(){
		if(Name.getText() == null || Name.getText().isEmpty()){
			NameLabel.setText("Please enter your name");
			return false;
		}

		//gets the index of @ and .
		int sIndex = Email.getText().indexOf("@");
		int Domain = Email.getText().indexOf(".");

		//checks if it found @ or .
		if (sIndex == -1 || Domain == -1) {
			EmailLabel.setText("Invalid email: missing '@' or '.'");
			return false;
		}
		else if(Email.getText().substring(sIndex + 1).isEmpty()
				|| Email.getText().substring(Domain + 1).isEmpty()){
			EmailLabel.setText("Invalid email: nothing after '@'");
			return false;
		}

		if(Password.getLength() < 8){
			PasswordLabel.setText("Password needs to be over 8 characters long");
			return false;
		}

		if(saveJson.CheckData(Email, Password)){

			return false;
		}

		//if no gender is selected it selects a random gender
		if(Genders.getSelectionModel().getSelectedIndex() == -1){
			int randomIndex = (int)(Math.random() * Genders.getItems().size());
			Genders.getSelectionModel().select(randomIndex);
		}

		_Name = Name.getText();
		_Password = Password.getText();
		_Email = Email.getText();
		_Gender = Genders.getSelectionModel().getSelectedItem();

		return true;

	}

	public void Signup(){
		if(CheckIfValid()){
			saveJson.saveData(_Name,_Password, _Email, _Gender);
		}
	}
}
