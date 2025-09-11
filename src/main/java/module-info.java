module nofal.nofapp {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires com.google.gson;

	// allow both FXML loader and Gson to use reflection
	opens nofal.nofapp to javafx.fxml, com.google.gson;

	exports nofal.nofapp;
}
