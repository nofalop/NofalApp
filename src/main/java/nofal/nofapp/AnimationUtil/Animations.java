package nofal.nofapp.AnimationUtil;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animations {

	public void BtnGrow(Button btn){
		ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
		btn.setOnMouseEntered(_ ->{
			st.stop();
			st.setDuration(Duration.millis(200));
			st.setToX(1.05); // 120% width
			st.setToY(1.05); // 120% height
			st.play();
		});
		btn.setOnMouseExited(_ -> {
			st.stop();
			st.setDuration(Duration.millis(200));
			st.setToX(1); // back to normal
			st.setToY(1);
			st.play();
		});
		btn.setOnMousePressed(_ -> {
			st.stop();
			st.setDuration(Duration.millis(50));
			st.setToX(0.95); // 120% width
			st.setToY(0.95); // 120% height
			st.play();
		});

		btn.setOnMouseReleased(_ -> {
			st.stop();
			st.setDuration(Duration.millis(100));

			st.setToX(1.05); // 120% width
			st.setToY(1.05); // 120% height
			st.play();
		});
	}
	public void BgMove(AnchorPane anchorPane){
		// Property to animate (acts like a slider from 0 → 1)
		DoubleProperty t = new SimpleDoubleProperty(0);

		// Listener: update gradient every time t changes
		t.addListener((obs, oldVal, newVal) -> {
			/*
			v = 0 => RED -> BLUE
			v = 0.5 => PURPLE -> TEAL
			v = 1 => PURPLE -> CYAN
			*/
			double v = newVal.doubleValue();

			LinearGradient gradient = new LinearGradient(
					//starts from top left ends at bottem right
					0, 0, 1, 1, true, null,
					new Stop(0, Color.RED.interpolate(Color.BLUE, v)),   // transition RED → BLUE
					new Stop(1, Color.PURPLE.interpolate(Color.CYAN, v))  // transition BLUE → GREEN
			);

			anchorPane.setBackground(
					new Background(new BackgroundFill(gradient, null, null))
			);
		});

		// Timeline animates t from 0 → 1
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(t, 0)),
				new KeyFrame(Duration.seconds(5), new KeyValue(t, 1))
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}
}

