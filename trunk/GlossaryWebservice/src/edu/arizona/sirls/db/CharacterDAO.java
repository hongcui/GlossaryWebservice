package edu.arizona.sirls.db;

import java.util.List;

import edu.arizona.sirls.beans.Term;

public class CharacterDAO extends AbstractDAO {

	private static CharacterDAO instance = null;
	
	private CharacterDAO () throws Exception { }

	public static CharacterDAO getInstance() throws Exception {
		if(instance == null)
			instance = new CharacterDAO();
		return instance;
	}

	public void setCharacters(List<Term> characters, String tablePrefix) throws Exception {
		this.openConnection();
		
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_possibleCharacters";
		this.executeSQL(sql).close();
		
		
		sql = "CREATE TABLE " + tablePrefix + "_possibleCharacters ( " +
				" `term` varchar(100) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_possibleCharacters WRITE";
		this.executeSQL(sql).close();
		
		for(Term term : characters) {
			sql = "INSERT INTO " + tablePrefix + "_possibleCharacters" + " VALUES ('" +
					term.getTerm() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();
		
		this.closeConnection();
	}

}
