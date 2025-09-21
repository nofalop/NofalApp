module nofal.nofapp {
	// JavaFX
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	// Extra libraries
	requires org.controlsfx.controls;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires com.google.gson;
	requires jdk.jfr;

	// Export packages (only if other modules need them)
	exports nofal.nofapp;

	// Open packages for FXML (reflection)
	opens nofal.nofapp to javafx.fxml, com.google.gson;
	opens nofal.nofapp.Gamemodes.WebEN to javafx.fxml;
	opens nofal.nofapp.Gamemodes.PacmanEN to javafx.fxml;
	exports nofal.nofapp.MainForm;
	opens nofal.nofapp.MainForm to com.google.gson, javafx.fxml;
	exports nofal.nofapp.managers;
	opens nofal.nofapp.managers to com.google.gson, javafx.fxml;
	exports nofal.nofapp.Gamemodes;
	opens nofal.nofapp.Gamemodes to com.google.gson, javafx.fxml;
}
