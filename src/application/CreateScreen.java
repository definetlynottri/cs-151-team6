package application;

import objects.Card;
import objects.Course;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateScreen {
	@FXML
	private TextField name;
	@FXML
	private Button create;
	
	public void CreateCourse(ActionEvent event) throws IOException{
		ArrayList<Card> newClist= new ArrayList<>();
		Course newCourse= new Course(Main.getCurrAcc().CourseList.size(),name.getText(), newClist);
		Main.getCurrAcc().addCourse(newCourse);
	}
}
