package nofal.nofapp.managers;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nofal.nofapp.MainForm.User;

public class JsonManager {
	// You can remove setPrettyPrinting() if you want compact one-line JSON
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final Type AccountType = new TypeToken<Map<String, User>>(){}.getType();
	private final Alert alert = new Alert(Alert.AlertType.ERROR);
	private final Path path = Paths.get("UsersInfo", "Info.json");
	private final File FILE = path.toFile();

	private Map<String, User> loadAccounts(){
		if(!FILE.exists() || FILE.length() == 0){
			return new HashMap<>();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
			return gson.fromJson(br, AccountType);
		}
		catch (IOException ex){
			return new HashMap<>();
		}
	}


	public void saveData(String name, String email, String password, String gender, String userpfp, boolean isOnline) {
		Map<String, User> accounts = loadAccounts();
		accounts.put(email, new User(name, email, password, gender, userpfp, isOnline));

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))){
			gson.toJson(accounts, AccountType, bw);
		}
		catch (IOException ex) {
			showErr(ex.getMessage());
		}
	}
	private void saveData(Map<String, User> accounts) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
			gson.toJson(accounts, AccountType, bw);
		} catch (IOException ex) {
			showErr(ex.getMessage());
		}
	}

	public boolean CheckData(TextField email,  PasswordField Password){
		try{
			Map<String, User> accounts = loadAccounts();
			if(accounts.isEmpty()){
				return false;
			}

			String emailValue = email.getText();
			String passwordValue = Password.getText();

			if(emailValue == null || emailValue.isBlank() || passwordValue.isEmpty()){
				return false;
			}

			for (User user : accounts.values()){
				if(user.getEmail().equals(emailValue) && user.getPassword().equals(passwordValue)){
					return true;
				}
			}
		}
		catch (OutOfMemoryError ex){
			showErr(ex.getMessage());
		}
		return false; // returns False if it doesn't find it
	}
	public User getUserByEmail(String email) {
		try {
			Map<String, User> accounts = loadAccounts();
			for (User user : accounts.values()) {
				if (email.equals(user.getEmail())) {
					return user; // fully populated user
				}
			}
		} catch (OutOfMemoryError ex) {
			showErr("err");
		}
		return null; // no user found
	}
	public void updateUserStatus(String email , boolean status){
		try {
			Map<String, User> accounts = loadAccounts();

			// find the user
			User user = accounts.get(email);
			if (user != null) {
				user.setUserStatus(status);

				// save back to file
				saveData(accounts);

			}
		} catch (Exception ex) {
			showErr("Error updating user status: " + ex.getMessage());
		}
	}

	public Map<String, User> loadUsers(){
		if(!FILE.exists() || FILE.length() == 0){
			return new HashMap<>();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
			return gson.fromJson(br, AccountType);
		}
		catch (IOException ex){
			return new HashMap<>();
		}
	}
	private void showErr(String msg) {
		alert.setTitle("Error");
		alert.setHeaderText("Something went wrong");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}