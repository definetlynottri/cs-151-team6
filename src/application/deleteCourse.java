package application;

import objects.Course;
import java.io.IOException;
//import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class deleteCourse {
	@FXML
	private TextField name;
	@FXML
	private Button delete;
	
	public void delCourse(ActionEvent event) throws IOException {
		//String name= Main.getCurrentCourse().getName();
		System.out.println(name.getText());
		Main.getCurrAcc().deleteCourse(name.getText());
		
		
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
