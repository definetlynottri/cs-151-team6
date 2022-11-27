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
	@FXML
	private Label question;
	
	@FXML
	private Label answer;
	
	@FXML
	private CheckBox checkBox;
	
	ArrayList<Card> CardList; // active list of cards, can be all, learned, or unlearned
	int CurrentIndex; // index of the active card
	Card CurrentCard; // cursor for the active card in the viewer
	String SortSelection= "All";
	
	/**
	 * Constructor, handles shuffled/unshuffled list logic and what to display if course is empty
	 */
	public ViewCards() {
		// Decides wether to load the shuffled or unshuffled list to the view
		if(Main.viewCardList == null) {
			this.CardList = Main.getCurrentCourse().CardList;
		} else {
			this.CardList = Main.viewCardList;
		}
		
		// Handles empty courses
		if(CardList.isEmpty()) {
			CurrentCard = new Card(-1, false, "Empty Course", "Empty Course");
			CurrentIndex = -1;
		} else {
			CurrentCard = CardList.get(0);
			CurrentIndex = 0;
		}
		
		
	}
	
	//shows question and answer of the first card 
	@FXML
	public void initialize() {
		updateCard();
	}
	
	/**
	 * Updates the card being shown, called in the next/prev card plus the initialize methods
	 */
	private void updateCard() {
		question.setText(CurrentCard.getUpperText());
		answer.setText(CurrentCard.getLowerText());
		checkBox.setSelected(CurrentCard.getLearned());
	}
	
	//exits to Course BRowser
	public void exit() throws IOException {
		Main.viewCardList = null; // clears the active list
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
			CurrentCard.UpdateLearned(true);
		}
		else {
			CurrentCard.UpdateLearned(false);
		}
	}
	
	/**
	 * Gets the next card in the list, wraps around to the start if necessary 
	 */
	public void NextCard() {
		if(CurrentIndex>=CardList.size()-1) {
			CurrentIndex=0;
			CurrentCard= CardList.get(0);
		}
		else {
			CurrentIndex++;
			CurrentCard= CardList.get(CurrentIndex);
		}
		updateCard();
	}
	
	/**
	 * Gets the previous card in the list, wraps around to the end if necessary
	 */
	public void PrevCard() {
		if(CurrentIndex<=0) {
			CurrentIndex=CardList.size()-1;
			CurrentCard= CardList.get(CurrentIndex);
		}
		else {
			CurrentIndex--;
			CurrentCard= CardList.get(CurrentIndex);
			
		}
		updateCard();
	}
	
	// goes to create new edit Screen with selected card
	public void EditCard() throws IOException {
		Main.currentCard=CurrentCard;
		Main m= new Main();
		m.changeScene("EditCard.fxml");
	}
	
	// deletes the card and shows next card
	public void DeleteCard() throws IOException {
		Main.getCurrentCourse().removeCard(this.CurrentCard); //update both memory and DB
		CardList.remove(this.CurrentIndex); // update the shuffled list
		if(CardList.isEmpty()) {
			CurrentCard = new Card(-1, false, "Empty Course", "Empty Course");
			CurrentIndex = -1;
			updateCard(); // updates the view to show empty course
		} else
			NextCard(); // gets the next card
	}
	
	/**
	 * Adds a card to memory, database, and if viewing a shuffled list of cards, the shuffled list as well
	 * @throws IOException
	 */
	public void AddCard() throws IOException {
		Card CurrentCard= new Card(-1,false,"insert text here", "insert text here");
		Main.currentCard=CurrentCard;
		Main.getCurrentCourse().addCard(CurrentCard); //update both memory and DB
		
		// adds on the card if the current list is shuffled
		if(Main.getCurrentCourse().CardList != Main.viewCardList) // checks if this is a shuffled list
			CardList.add(CurrentCard); // adds the new card to the shuffled card list
		
		// Updates scene/info
		this.CurrentIndex = CardList.size()-1;
		updateCard(); // update the card info on addition
		Main m= new Main();
		//changed edit card to add card
		m.changeScene("AddCard.fxml");
	}
	
}
