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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

//show card of course
public class viewCourse implements Initializable{

	@FXML
	private ListView<String> myListView;
	
	@FXML
	private Label myLabel;
	
	@FXML
	private Button back;
	
	String[] course = {"A","B","C"};
	
	String currentCourse;
	
	//show cards as a list 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		myListView.getItems().addAll(course);
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				currentCourse = myListView.getSelectionModel().getSelectedItem();
				myLabel.setText(currentCourse);
			}	
		});
	}
	//implement return to mainpage function 
	public void backToBrowser(ActionEvent event) throws IOException {
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
