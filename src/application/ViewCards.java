package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import objects.Card;
import objects.Course;

public class ViewCards {
	//Both are Empty labels that will print card question and answer
	//Can't check if it works as it seems there is error in this class
	@FXML
	private Label question;
	
	@FXML
	private Label answer;
	
	@FXML
	private CheckBox checkBox;
	
	ArrayList<Card> CardList= Main.getCurrentCourse().CardList;
	int CurrentIndex=0;
	Card CurrentCard= CardList.isEmpty()?(new Card(-1, false, "Empty Course", "Empty Course")):CardList.get(0);
	String SortSelection= "All";
	
	
	//exits to Course BRowser
	public void exit() throws IOException {
		Main m= new Main();
		m.changeScene("courseBrowser.fxml");
	}
	
	//lead to a seperated class so that users can choose Learned, Unlearned or All
	public void shuffleCard() throws IOException {
		Main m= new Main();
		m.changeScene("shuffleCard.fxml");
	}
	
	//handle check box
	public void updateLearned(ActionEvent event) {
		if(checkBox.isSelected()) {
			//update card is learned
		}
		else {
			//update card is not learned
		}
	}
	
	// I have added simple methods to get an ArrayList of learned/unlearned/all cards in the Course.java file
	//I'm not sure how can user select Learned, Unlearned or All in this method,
	//so I made a seperated class so that users can choose Learned, Unlearned or All
	//If anyone have an idea for this, plz share it. 
	
	//shuffles the deck according to the condition of Learned Unlearned or All
//	public void Shuffle(){
//		ArrayList<Card> CardList= Main.getCurrentCourse().CardList;
//		HashSet <Card>  Shuffled= new HashSet<>();
//		if(SortSelection== "Learned") {
//			for(int i=0; i< CardList.size(); i++ ) {
//				if(CardList.get(i).getLearned()==true) {
//					Shuffled.add(CardList.get(i));
//				}
//			}
//		}
//		else if(SortSelection== "Not Learned"){
//			for(int i=0; i< CardList.size(); i++ ){
//				if(CardList.get(i).getLearned()==false){
//					Shuffled.add(CardList.get(i));
//				}
//			}
//		}
//		else{
//			for(int i=0; i< CardList.size(); i++ ){
//				Shuffled.add(CardList.get(i));
//			}
//		}
//		CardList = new ArrayList<>(Shuffled);
//		CurrentIndex=0;
//		CurrentCard= CardList.get(0);
//		
//	}
	
	//views next card in list
	public void NextCard() {
		if(CurrentIndex==CardList.size()-1) {
			CurrentIndex=0;
			CurrentCard= CardList.get(0);
		}
		else {
			CurrentIndex++;
			CurrentCard= CardList.get(CurrentIndex);
		}
		CurrentCard.UpdateLearned();
	}
	// views previous card in List
	public void PrevCard() {
		if(CurrentIndex==0) {
			CurrentIndex=CardList.size()-1;
			CurrentCard= CardList.get(CurrentIndex);
		}
		else {
			CurrentIndex--;
			CurrentCard= CardList.get(CurrentIndex);
			
		}
		CurrentCard.UpdateLearned();
	}
	
	// goes to create new edit Screen with selected card
	public void EditCard() throws IOException {
		Main.currentCard=CurrentCard;
		Main m= new Main();
		m.changeScene("EditCard.fxml");
	}
	
	// need to implement delete it from DB and show next card
		public void DeleteCard() throws IOException {
			NextCard();
		}
	
	// HAVE NOT ADDED SQL ID, USED PLACEHOLDER 0, creates new card then goes to edit screen
	public void AddCard() throws IOException {
		Card CurrentCard= new Card(0,false,"insert text here", "insert text here");
		Main.currentCard=CurrentCard;
		Main.getCurrentCourse().addCard(CurrentCard);
		Main m= new Main();
		m.changeScene("EditCard.fxml");
	}
	
}
