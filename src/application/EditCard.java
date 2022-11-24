package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.Card;
import sql.DBAccess;
public class EditCard {
	@FXML
	private TextField UT;
	@FXML
	private TextField LT;
	@FXML
	private Button Finished;
	
	
	Card CurrentCard= Main.currentCard;
	
	
	public void FinishedCard() throws IOException{
		CurrentCard.UpdateCard(UT.getText(), LT.getText());
		Main m = new Main();
		m.changeScene("ViewCards.fxml");
	}
}
