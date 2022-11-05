/*
 * Roman's Database interface spaghetti 
 */
package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Account;
import objects.Card;
import objects.Course;

public class DBAccess {
	/**
	 * Inserts a new account into the database
	 * @param username the username
	 * @param password the ENCRYPTED password
	 */
	public final static boolean insertAccount(String username, String password, String securityQuestion) {
		String sql = "INSERT INTO accounts(username,password,securityQuestion) VALUES(?,?,?)";
		try {
			PreparedStatement pStatement = DBConnect.getConnection().prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			pStatement.setString(3, securityQuestion);
			pStatement.executeUpdate();
			pStatement.close();
			return true;
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return false;
	}
	
	/**
	 * Inserts a new course into the database
	 * @param name Name of the course
	 * @param accountID Primary Key for the corresponding account
	 */
	public final static int insertCourse(String name, int accountID) {
		int key = -1;
		String sql = "INSERT INTO courses(name,accountID) VALUES(?,?)";
		try {
			PreparedStatement pStatement = DBConnect.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, name);
			pStatement.setInt(2, accountID);
			pStatement.executeUpdate();
			pStatement.close();
			
			ResultSet rs = pStatement.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    key = (int)rs.getLong(1);
			}
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return key;
	}
	
	/**
	 * Inserts a card into the database
	 * @param question the question part of the card
	 * @param answer the answer part of the card
	 * @param learned if the card is learned or not
	 * @param courseID the course the card corresponds to
	 */
	public final static int insertCard(int courseID, boolean learned, String upperText, String lowerText) {
		String sql = "INSERT INTO cards(upperText,lowerText,learned,courseID) VALUES(?,?,?,?)";
		int key = -1;
		try {
			PreparedStatement pStatement = DBConnect.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, upperText);
			pStatement.setString(2, lowerText);
			pStatement.setInt(3, learned?1:0);
			pStatement.setInt(4, courseID);
			pStatement.executeUpdate();
			
			// get the primary key
			ResultSet rs = pStatement.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    key = (int)rs.getLong(1);
			}
			//System.out.println("Key:" + key);
			
			// close the statement 
			pStatement.close();
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return key;
	}
	
	
	/**
	 * Updates information for a card
	 * @param cardID Required, the db id of the card
	 * @param learned Required, the learned status of the card
	 * @param question optional, enter null to not update
	 * @param answer optional, enter null to not update
	 */
	public final static void modifyCard(int cardID, boolean learned, String question, String answer) {
		StringBuilder sql = new StringBuilder("UPDATE cards SET");
		sql.append(" learned = " + (learned?1:0));
		if(question != null)
			sql.append(", upperText = \"" + question + "\"");
		if(answer != null)
			sql.append(", lowerText = \"" + answer + "\"");
		sql.append(" WHERE id = " + cardID);
		System.out.println(sql.toString());
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql.toString());
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Updates the name of a specified course
	 * @param courseID the course ID in the database
	 * @param name the new name for the course
	 */
	public final static void modifyCourse(int courseID, String name) {
		String sql = "UPDATE courses SET name = \"" + name + "\" WHERE id = " + courseID;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql);
			statement.close();
			
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Modifies the password for an account
	 * @param accountID the account database id for the password change
	 * @param password the new password
	 */
	public final static void modifyPassword(int accountID, String password) {
		String sql = "UPDATE accounts SET password = \"" + password + "\" WHERE id = " + accountID;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql);
			statement.close();
			
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Resets the password based on the security answer
	 * @param username	the username of the account
	 * @param securityAnswer the security Answer
	 * @param password the new password
	 * @return true if successful, false otherwise
	 */
	public final static boolean resetPassword(String username, String securityAnswer, String password) {
		String sql = "UPDATE accounts SET password=? WHERE username=? AND securityQuestion=?";
		try {
			PreparedStatement pStatement = DBConnect.getConnection().prepareStatement(sql);
			pStatement.setString(1, password);
			pStatement.setString(2, username);
			pStatement.setString(3, securityAnswer);
			int result = pStatement.executeUpdate();
			pStatement.close();
			if(result>0)
				return true;
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return false;
	}
	
	/**
	 * Deletes a card from the database
	 * @param cardID the primary key for the card to delete
	 */
	public final static void deleteCard(int cardID) {
		String sql = "DELETE FROM cards WHERE id = " + cardID;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Deletes a course from the db
	 * @param courseID the primary key of the course to delete
	 */
	public final static void deleteCourse(int courseID) {
		String sql = "DELETE FROM courses WHERE id = " + courseID;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Deletes the account
	 * @param accountID the primary key of the account to delete
	 */
	public final static void deleteAccount(int accountID) {
		String sql = "DELETE FROM accounts WHERE id = " + accountID;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	/**
	 * Gets an ArrayList<Card> of a desired course
	 * @param courseID the id of the course
	 * @return an ArrayList<Card> for the entered course
	 */
	public final static ArrayList<Card> getCards(int courseID) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, learned, upperText, lowerText  FROM cards WHERE courseID=" + courseID);
			while(rs.next()) {
				// gets the values from the database
				int cardID = rs.getInt("id");
				boolean learned = (rs.getInt("learned")==0)?false:true;
				String uText = rs.getString("upperText");
				String lText = rs.getString("lowerText");
				
				// Creates and adds the card
				Card tCard = new Card(cardID, learned, uText, lText);
				cardList.add(tCard); // adds on the card to the list
			}
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return cardList;
	}
	
	/**
	 * Gets an arraylist of courses
	 * @param accountID the id of the account to find courses for
	 * @return an arraylist of courses for a given account
	 */
	public final static ArrayList<Course> getCourses(int accountID) {
		ArrayList<Course> courseList = new ArrayList<Course>();
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, name FROM courses WHERE accountID=" + accountID);
			while(rs.next()) {
				// gets the values from the database
				int courseID = rs.getInt("id");
				String cName = rs.getString("name");
				
				// Creates and adds the card
				Course tCourse = new Course(courseID, cName, getCards(courseID));
				courseList.add(tCourse); // adds on the card to the list
			}
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return courseList;
	}
	
	/**
	 * Loads a course from the database
	 * @param courseID primary key of the course to load
	 * @return Course object of the chosen ID
	 */
	public final static Course loadCourse(int courseID) {
		Course course = null;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, name FROM courses WHERE id =" + courseID);
			if(rs.next()) {
				ArrayList<Card> arrCards = DBAccess.getCards(courseID);
				course = new Course(rs.getInt("id"), rs.getString("name"), arrCards);
			}
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return course;
	}
	
	/**
	 * Loads an account object from a database
	 * @param accountID the account id
	 * @return an Account object with data from the database
	 */
	public final static Account loadAccount(int accountID) {
		Account account = null;
		try {
			Statement statement = DBConnect.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, username, password, securityQuestion FROM accounts WHERE id =" + accountID);
			if(rs.next()) {
				int id = rs.getInt("id");
				String uName = rs.getString("username");
				String pass = rs.getString("password");
				String sQuest = rs.getString("securityQuestion");
				ArrayList<Course> arrCards = DBAccess.getCourses(accountID);
				account = new Account(id, uName, pass, sQuest, arrCards);
			}
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return account;
	}
	
	/**
	 * Checks whether a username and password matches, returns the primary key if they do
	 * @param username the username of the account
	 * @param password the password of the account
	 * @return the primary key of the account with the account+password combination
	 */
	public final static int getAccount(String username, String password) {
		String sql = "SELECT id FROM accounts WHERE username=? AND password=?";
		try {
			PreparedStatement pStatement = DBConnect.getConnection().prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			ResultSet rs = pStatement.executeQuery();
			// get results
			if(rs.next())
				return rs.getInt("id"); // returns the resultant account id
		} catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		return -1; // return -1 if incorrect
	}
	
}
