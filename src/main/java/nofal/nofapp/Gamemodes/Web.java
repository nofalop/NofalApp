package nofal.nofapp.Gamemodes;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Web {
	@FXML
	private ImageView goBack,goForward,refreshWeb,userHistory;

	@FXML
	private WebView WV;

	@FXML
	private TextField URLText;

	private WebEngine engine;

	private WebHistory webHistory;

	private double webZoom;

	@FXML
	public  void initialize(){
		engine = WV.getEngine();
		URLText.setText("google");
		webZoom = 1;
		loadPage();

		refreshWeb.setOnMouseClicked(_ -> engine.reload());

		//Goes back to the other Page
		goBack.setOnMouseClicked(_ -> {
			webHistory = engine.getHistory();

			ObservableList<WebHistory.Entry> entries = webHistory.getEntries();

			webHistory.go(-1);

			URLText.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
		});

		//Goes to the previous page
		goForward.setOnMouseClicked(_ -> {
			webHistory = engine.getHistory();

			ObservableList<WebHistory.Entry> entries = webHistory.getEntries();

			webHistory.go(1);

			URLText.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
		});

		//when user presses enter on the TextFiled it loads the page
		URLText.setOnKeyPressed(keyEvent -> {
			switch (keyEvent.getCode()){
				case ENTER -> loadPage();
			}
		});

		//Check if the user pressed Ctrl + or Ctrl - for the zoom
		WV.setOnKeyPressed(KeyEvent ->{
			if(KeyEvent.isControlDown() && KeyEvent.getCode() == KeyCode.MINUS){
				webZoom -= 0.25;
				WV.setZoom(webZoom);
			}
			else if (KeyEvent.isControlDown() && KeyEvent.getCode() == KeyCode.EQUALS) {
				webZoom += 0.25;
				WV.setZoom(webZoom);
			}
		});



	}

	private void loadPage(){
		engine.load("https://" + URLText.getText() + ".com");
	}
}
