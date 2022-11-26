package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.Card;
import sql.DBAccess;
public class shuffleCard {
	@FXML
	private Button Learned;
	@FXML
	private Button notLearned;
	@FXML
	private Button all;
	
	ArrayList<Card> CardList= Main.getCurrentCourse().CardList;
	int CurrentIndex=0;
	Card CurrentCard= CardList.get(0);
	String SortSelection= "All";
	

	
	HashSet <Card>  Shuffled= new HashSet<>();
	public void learned() throws IOException {

		for(int i=0; i< CardList.size(); i++ ) {
			if(CardList.get(i).getLearned()==true) {
				Shuffled.add(CardList.get(i));
			}
		}
		CardList = new ArrayList<>(Shuffled);
		CurrentIndex=0;
		CurrentCard= CardList.get(0);
	}
	
	public void notLearned() throws IOException {

			for(int i=0; i< CardList.size(); i++ ){
				if(CardList.get(i).getLearned()==false){
					Shuffled.add(CardList.get(i));
				}
			}
			CardList = new ArrayList<>(Shuffled);
			CurrentIndex=0;
			CurrentCard= CardList.get(0);
	}	
	
	public void shuffleAll() throws IOException {
		for(int i=0; i< CardList.size(); i++ ){
			Shuffled.add(CardList.get(i));
		}
		CardList = new ArrayList<>(Shuffled);
		CurrentIndex=0;
		CurrentCard= CardList.get(0);
	}
	
	public void backToViewCard() throws IOException {
		Main m= new Main();
		m.changeScene("viewCards.fxml");
	} 
}
