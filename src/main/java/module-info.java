module nofal.nofapp {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;

	opens nofal.nofapp to javafx.fxml;
	exports nofal.nofapp;
}