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

	// Export packages (so other modules can use your classes)
	exports nofal.nofapp;
	exports nofal.nofapp.Gamemodes;

	// Open packages for reflection (FXML + Gson)
	opens nofal.nofapp to javafx.fxml, com.google.gson;
	opens nofal.nofapp.Gamemodes to javafx.fxml;
}
