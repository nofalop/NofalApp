package nofal.nofapp.Gamemodes.PacmanEN;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static com.sun.scenario.effect.impl.prism.PrEffectHelper.render;
import static jdk.jfr.internal.consumer.EventLog.update;

public class Pacman {
	/// 0 = empty
	/// 1 = wall
	/// 2 = pellet
	/// 3 = power pellet
	/// 4 = player start
	/// 5 = ghost liar
	private int[][] map = {
			{1,1,1,1,1,1,1},
			{1,2,2,2,2,2,1},
			{1,2,1,1,1,2,1},
			{1,2,2,4,2,2,1},
			{1,1,1,1,1,1,1}
	};

	private final int TILE_SIZE = 24;
	//pos
	private int PacmanRow = 3, PacmanColumn = 4;

	public void initialize(){
		PacmanWindow();
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				render();
			}
		}.start();

	}

	private void PacmanWindow(){
		Pane root = new Pane();
		ImageView Pacman = new ImageView(new Image("C:\\Users\\L-G\\OneDrive\\Desktop\\Code\\Java\\Nofal_app\\NofApp\\src\\main\\resources\\nofal\\nofapp\\Images\\Pacman\\PacMan.png"));

		Pacman.setFitWidth(TILE_SIZE);
		Pacman.setFitHeight(TILE_SIZE);
		root.getChildren().add(Pacman);

		Scene scene = new Scene(root, 600, 600, Color.BLACK);

		scene.setOnKeyPressed(KeyEvent ->{
			switch(KeyEvent.getCode()){
				case UP, W -> PacmanColumn--;
				case DOWN, S -> PacmanColumn++;
				case LEFT,A -> PacmanRow--;
				case RIGHT,D -> PacmanRow++;
			}
		});


	}

	private void render(){

	}
}
