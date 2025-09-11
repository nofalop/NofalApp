package nofal.nofapp;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;

public class SaveJson {
	// You can remove setPrettyPrinting() if you want compact one-line JSON
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final Type AccountType = new TypeToken<Map<String, User>>(){}.getType();
	private final Alert alert = new Alert(Alert.AlertType.ERROR);
	private final File FILE = new File("C:\\Users\\L-G\\OneDrive\\Desktop\\Code\\Java\\Nofal_app\\NofApp\\src\\main\\resources\\nofal\\nofapp\\UsersInfo\\Info.json");

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


	public void saveData(String name, String email, String password, String gender) {
		Map<String, User> accounts = loadAccounts();
		accounts.put(email, new User(name, email, password, gender));

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))){
			gson.toJson(accounts, AccountType, bw);
		}
		catch (IOException ex) {
			showErr(ex.getMessage());
		}
	}

	private void showErr(String msg) {
		alert.setTitle("Error");
		alert.setHeaderText("Something went wrong");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
