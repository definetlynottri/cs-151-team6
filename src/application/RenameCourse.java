package application;

import objects.Course;
import java.io.IOException;
//import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RenameCourse {
	@FXML
	private TextField name;
	@FXML
	private Button rename;
	
	public void CreateCourse(ActionEvent event) throws IOException {
		String name= Main.getCurrentCourse().getName();
		 Main m = new Main();
	     m.changeScene("courseBrowser.fxml");
	}
}