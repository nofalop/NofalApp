package nofal.nofapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Controller {
	@FXML
	private Button Loginbtn,SignUpbtn;

	@FXML
	private ComboBox<String> Genders;


	@FXML
	public void initialize(){
		Genders.getItems().addAll("Male", "Female");
	}
}