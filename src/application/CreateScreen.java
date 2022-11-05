package application;

import objects.Course;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class CreateScreen {
	@FXML
	private TextField name;
	@FXML
	private Button create;
	
	public void CreateCourse(ActionEvent event) throws IOexception{
		ArrayList<Card> newClist= new ArrayList<>();
		Course newCourse= course(Main.getCurrAcc().CourseList.size(),name.getText(), newClist);
		Main.getCurrAcc().addCourse(newCourse);
	}
}
