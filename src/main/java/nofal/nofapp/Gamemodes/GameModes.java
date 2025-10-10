package nofal.nofapp.Gamemodes;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import nofal.nofapp.AnimationUtil.Animations;
import nofal.nofapp.Gamemodes.ChatEN.Server.ChatHttpsServer;
import nofal.nofapp.Gamemodes.ChatEN.onlineChat;
import nofal.nofapp.MainForm.User;
import nofal.nofapp.managers.JsonManager;
import nofal.nofapp.managers.SceneManager;

public class GameModes {
	private final Animations animations = new Animations();
	private final ChatHttpsServer chatHttpsServer = new ChatHttpsServer();
	private final JsonManager jsonManager = new JsonManager();
	private User currentUser;

	@FXML private Button Chatbtn,googleBtn;
	@FXML private AnchorPane ACPBg;

	public void setUser(User user){
		this.currentUser = user;
	}

	public void initialize(){
		animations.BtnGrow(Chatbtn);
		animations.BtnGrow(googleBtn);
		animations.BgMove(ACPBg);
	}

	public void StartChat() throws IOException {
		onlineChat chatController = SceneManager.openNewWindowGetC("/nofal/nofapp/Chat.fxml", "nofdis");
		if (currentUser != null) {
			chatController.setUser(currentUser);
		}
		assert currentUser != null;
		jsonManager.updateUserStatus(currentUser.getEmail(), true);
		chatHttpsServer.start(8080);

	}

	public void openGoogle() throws IOException {
		SceneManager.openNewWindow("/nofal/nofapp/Web.fxml", "Web");
	}
}
