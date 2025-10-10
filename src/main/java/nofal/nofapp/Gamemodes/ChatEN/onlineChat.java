package nofal.nofapp.Gamemodes.ChatEN;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import nofal.nofapp.AnimationUtil.Animations;
import nofal.nofapp.Gamemodes.ChatEN.Server.ChatClient;
import nofal.nofapp.MainForm.User;
import nofal.nofapp.managers.JsonManager;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class onlineChat {
	private final Animations animations = new Animations();
	private final JsonManager jsonManager = new JsonManager();

	@FXML private AnchorPane ACPBg;
	@FXML private VBox users, userChatCont;
	@FXML private ImageView userpfp;
	@FXML private Label userLabel;
	@FXML private TextField Search, userMsg;

	private User currentUser;

	public void setUser(User user) {
		this.currentUser = user;
		if (userLabel != null) {
			updateUserLabel();
		}
	}

	private void updateUserLabel() {
		if (currentUser != null && !currentUser.getName().isBlank()) {
			userLabel.setText(currentUser.getName() + "  " + "(You)");
			userpfp.setImage(new Image(getClass().getResource("/nofal/nofapp/Images/ChatImg/image.png").toExternalForm()));
			loadOnlineUsers();
		} else {
			userLabel.setText("NoName");
		}
	}

	@FXML
	public void initialize() {
		animations.BgMove(ACPBg);
		if (currentUser != null) {
			updateUserLabel();
		}
		userMsg.setOnKeyPressed(KeyEvent -> {
			if (!(Objects.requireNonNull(KeyEvent.getCode()) == KeyCode.ENTER)) {
				return;
			}
			String msg = userMsg.getText().trim();
			if (msg.isEmpty()) {
				return;
			}
			HBox messageBox = new HBox(10);
			messageBox.setAlignment(Pos.TOP_RIGHT); // Right for current user

			ImageView userPfp = new ImageView(
					new Image(
							getClass()
									.getResource(currentUser.getUserPfp())
									.toExternalForm()
					)
			);
			userPfp.setFitWidth(35);
			userPfp.setFitHeight(35);
			userPfp.setClip(new Circle(17.5, 17.5, 17.5));

			Label messageLabel = new Label(msg);
			messageLabel.getStyleClass().add("message-label");

			messageBox.getChildren().addAll(messageLabel, userPfp);
			userChatCont.getChildren().add(messageBox);

			ChatClient.sendMessage(currentUser.getName(), msg);
			System.out.println("msg sent");
			userMsg.clear();
		});
	}

	private void openChatWithUser(User user){
		userChatCont.getChildren().clear();
		currentUser = user;
		List<Map<String, String>> chatHistory = jsonManager.loadChat(currentUser.getName(), user.getName());
		if(chatHistory == null){
			return;
		}
		for(Map<String, String> msg : chatHistory){
			String sender = msg.get("sender");
			String messageText = msg.get("message");

			Label messageLabel = new Label(messageText);
			messageLabel.getStyleClass().add("message-label");

			HBox messageBox = new HBox();
			messageBox.setPadding(new Insets(5));

			if(sender.equals(currentUser.getName())){
				messageLabel.setAlignment(Pos.TOP_RIGHT);
				messageBox.getStyleClass().add("sent-message");
			}
			else {
				messageBox.setAlignment(Pos.TOP_LEFT);
				messageLabel.getStyleClass().add("received-message");
			}
			messageBox.getChildren().add(messageLabel);
			userChatCont.getChildren().add(messageBox);
		}
		System.out.println("Loaded chat with " + user.getName());
	}


	private void loadOnlineUsers(){
		Map<String, User> userMap = jsonManager.loadUsers();
		users.getChildren().clear();

		for(User user: userMap.values()){
			if(currentUser.getName().equals(user.getName())){
				continue;
			}
			// Create container for each user
			HBox userBox = new HBox(10); // spacing = 10
			userBox.setPadding(new Insets(5));
			userBox.setStyle(
					"-fx-background-color: rgba(255, 255, 255, 0.15);" + // transparent white bg
							"-fx-background-radius: 10;" +
							"-fx-border-radius: 10;" +
							"-fx-border-color: rgba(255, 255, 255, 0.3);" +
							"-fx-border-width: 1;"
			);


			// Profile pic
			ImageView img = new ImageView(
					new Image(
							getClass()
							.getResource(user.getUserPfp())
							.toExternalForm()
					)
			);
			img.setFitWidth(40);
			img.setFitHeight(40);

			// Username label
			Label name = new Label(user.getName());
			name.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

			// Add them to box
			userBox.getChildren().addAll(img, name);

			userBox.setOnMouseClicked(e ->{
				openChatWithUser(currentUser);
			});

			// Add to VBox
			users.getChildren().add(userBox);
		}
	}
}