package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
	private Label invalidInput;
	ArrayList<String> courseNames;
	ArrayList<Course> courseIndexes;
	
	String currentCourse;
	
	//show cards as a list 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.courseNames = new ArrayList<String>();
		this.courseIndexes = new ArrayList<Course>();
		
		// populate the two listview lists
		for(Course c:Main.getCurrAcc().CourseList) {
			courseNames.add(c.getName()); // add the name of the course
			courseIndexes.add(c); // add the course index
		}

		myListView.getItems().addAll(courseNames);
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				currentCourse = myListView.getSelectionModel().getSelectedItem();
				myLabel.setText(currentCourse);
			}	
		});
	}
	
	
	/**
	 * Gets the selection from the listview and goes to the course's cards
	 */
	public void goToViewCards(ActionEvent event) throws IOException {
		Main m = new Main();
		int selection = myListView.getSelectionModel().getSelectedIndex(); // gets the selected index
		Main.setCurrentCourse(courseIndexes.get(selection)); // changes the course to the selection
		m.changeScene("viewCards.fxml"); // pops the view cards screen
		
		/*
		// check course name is valid
		if(courseName.getText().isEmpty()) {
			invalidInput.setText("Please enter your data.");
		}
		for (int i = 0; i < courseNames.size(); i++) {
			if (courseNames.get(i).equals(courseName.getText().toString())) {
				Course inCourse = Main.getCurrAcc().findCourse(courseName.getText().toString()); // gets the selected course
				Main.setCurrentCourse(inCourse); // updates the working course
				m.changeScene("viewCards.fxml");
			} 
			if(i == courseNames.size()-1) {
				invalidInput.setText("Invalid course name");
			}
		}
		*/
	}
	
	//implement return to mainpage function 
	public void backToBrowser(ActionEvent event) throws IOException {
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
