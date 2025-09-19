package nofal.nofapp.Gamemodes.WebEN;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;
import java.net.URI;
import java.net.URISyntaxException;

public class UHController {


	@FXML private HBox Container;
	@FXML private VBox UHContainer;

	public void initialize(){
		Container.getStylesheets().add(getClass().getResource("/nofal/nofapp/Styles/WebStyle.css").toExternalForm());
	}


	public void loadUserHistory(WebHistory UserWH){
		if(UserWH == null) return;

		ObservableList<WebHistory.Entry> entries = UserWH.getEntries();

		UHContainer.getChildren().clear();

		for (WebHistory.Entry entry: entries) {
			if(entry.getUrl() == null && entry.getUrl().isEmpty()){
				return;
			}
			String displayUrl = entry.getUrl();
			if(displayUrl.startsWith("https://")){
				displayUrl = displayUrl.substring(8);
			}

			TextField label = new TextField(displayUrl);
			label.setEditable(false);
			label.getStyleClass().add("UserHistory-Label");
			UHContainer.getChildren().add(label);
		}

	}
}
