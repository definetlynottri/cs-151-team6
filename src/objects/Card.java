/**
 * Holds the card logic
 */

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
	
	/**
	 * Updates the learned status of a card
	 * @param inLearned the boolean value of a card's learned status
	 * @return true if successful, false if something went wrong (card doesn't exist)
	 */
	public Boolean UpdateLearned(boolean inLearned) {
		boolean successful = DBAccess.modifyCard(cardID, inLearned, null, null); // modifies the card in DB
		if(successful)
			this.learned= inLearned; // modifies the card in memory if succesful
		return successful;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) // checks if the jvm ids match
			return true;
		
		// returns false if object is not an instance of cards
		if(!(o instanceof Card))
			return false;
		
		// Cast and compare members
		Card compareCard =  (Card) o;
		if(this.cardID == compareCard.cardID)
			return true;
		else
			return false;
		
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
