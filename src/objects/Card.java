package objects;
import sql.DBAccess ;

public class Card {
	String UpperText;
	String LowerText;
	boolean learned= false;
	int cardID;
	
	public Card(String UT, String LT) {
		this.UpperText= UT;
		this.LowerText= LT;
	}
	
	public Card(int cardID, boolean learned, String UT, String LT) {
		this.UpperText= UT;
		this.LowerText= LT;
		this.cardID = cardID;
		this.learned = learned;
	}
	
	public Boolean UpdateCard(String UT, String LT) {
		if(UT == null)
			UT = this.UpperText;
		if(LT == null)
			LT = this.LowerText;
		this.UpperText = UT;
		this.LowerText = LT;
		DBAccess.modifyCard(cardID, this.learned, UT, LT); 
		return true;
	}
	public Boolean UpdateLearned() {
		this.learned= true;
		DBAccess.modifyCard(cardID, this.learned, null, null);
		return true;
	}
	
	public String getUpperText() {return UpperText;}
	public String getLowerText() {return LowerText;}
	public boolean getLearned() {return learned;}
	public void setCardID(int cardID) {this.cardID = cardID;};
	
	/**
	 * Returns a readable string for logging
	 */
	public String toString() {return String.format("Card(ID:%d - upperText:\"%s\" - lowerText:\"%s\" - learned:%b", 
													this.cardID, this.UpperText, this.LowerText, this.learned);};
}
