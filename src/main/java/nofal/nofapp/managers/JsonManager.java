package nofal.nofapp.managers;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private final Type chatType = new TypeToken<Map<String, Map<String, List<Map<String, String>>>>>() {}.getType();

	private final Alert alert = new Alert(Alert.AlertType.ERROR);

	private final Path UserInfopath = Paths.get("UsersInfo", "Info.json");
	private final File UserInfoFile = UserInfopath.toFile();

	private final Path UserChatpath = Paths.get("userChat", "userchat.json");
	private final File UserChatFile = UserChatpath.toFile();




	public void saveData(String name, String email, String password, String gender, String userpfp, boolean isOnline) {
		Map<String, User> accounts = loadAccounts();
		accounts.put(email, new User(name, email, password, gender, userpfp, isOnline));

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(UserInfoFile))){
			gson.toJson(accounts, AccountType, bw);
		}
		catch (IOException ex) {
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
		if(!UserInfoFile.exists() || UserInfoFile.length() == 0){
			return new HashMap<>();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(UserInfoFile))){
			return gson.fromJson(br, AccountType);
		}
		catch (IOException ex){
			return new HashMap<>();
		}
	}

	public List<Map<String, String>> loadChat(String user1, String user2){
		try{
			Map<String, Map<String, List<Map<String, String>>>> allChat = gson.fromJson(new FileReader(UserChatFile), chatType);

			/*
			* Checks if user1 exists in the chat file
			* then checks if user1 has any specific chat with user 2
			* */
			if(allChat == null){
				return  new ArrayList<>();
			}

			if(allChat.containsKey(user1) && allChat.get(user1).containsKey(user2)){
				return allChat.get(user1).get(user2);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private void saveData(Map<String, User> accounts) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(UserInfoFile))) {
			gson.toJson(accounts, AccountType, bw);
		} catch (IOException ex) {
			showErr(ex.getMessage());
		}
	}

	private Map<String, User> loadAccounts(){
		if(!UserInfoFile.exists() || UserInfoFile.length() == 0){
			return new HashMap<>();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(UserInfoFile))){
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
