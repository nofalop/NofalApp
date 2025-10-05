package nofal.nofapp.Gamemodes.ChatEN;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nofal.nofapp.AnimationUtil.Animations;
import nofal.nofapp.MainForm.User;
import nofal.nofapp.managers.JsonManager;

import java.util.Map;

public class onlineChat {
	private final Animations animations = new Animations();
	private final JsonManager jsonManager = new JsonManager();

	@FXML private AnchorPane ACPBg, userChat;
	@FXML private VBox users;
	@FXML private ImageView userpfp;
	@FXML private Label userLabel;
	@FXML private TextField Search;

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
		if (currentUser != null) {
			updateUserLabel();
		}
		animations.BgMove(ACPBg);
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

			// Add to VBox
			users.getChildren().add(userBox);
		}
	}
}