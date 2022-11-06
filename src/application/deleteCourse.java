package application;

import objects.Course;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

//delete course
public class deleteCourse {
	@FXML
	private TextField name;
	@FXML
	private Button delete;
	
	//delete course user entered and automatically go back to mainpage
	public void delCourse(ActionEvent event) throws IOException {
		Main.getCurrAcc().deleteCourse(name.getText());
		System.out.println(Main.getCurrAcc());
		
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
