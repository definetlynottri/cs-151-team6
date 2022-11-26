package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import objects.Course;

//show card of course
public class viewCourse implements Initializable{

	@FXML
	private ListView<String> myListView;
	
	@FXML
	private Label myLabel;
	
	@FXML
	private Button back;
	
	@FXML
	private Button openCourse;
	
	@FXML
	private TextField courseName;
	
	@FXML
	private Label invalidInput;
	//change this one to data from DB
	String[] course = {"A","B","C"};
	
	String currentCourse;
	
	//show cards as a list 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		course = Main.getCurrAcc().getCourseNames();
		myListView.getItems().addAll(course);
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				currentCourse = myListView.getSelectionModel().getSelectedItem();
				myLabel.setText(currentCourse);
			}	
		});
	}
	
	
	public void goToViewCards(ActionEvent event) throws IOException {
		Main m = new Main();
		// check course name is valid
		if(courseName.getText().isEmpty()) {
			invalidInput.setText("Please enter your data.");
		}
		for (int i = 0; i < course.length; i++) {
			if (course[i].equals(courseName.getText().toString())) {
				Course inCourse = Main.getCurrAcc().findCourse(courseName.getText().toString()); // gets the selected course
				Main.setCurrentCourse(inCourse); // updates the working course
				m.changeScene("viewCards.fxml");
			} 
			if(i == course.length-1) {
				invalidInput.setText("Invalid course name");
			}
		}
	}
	
	//implement return to mainpage function 
	public void backToBrowser(ActionEvent event) throws IOException {
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
