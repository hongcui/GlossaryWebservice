package edu.arizona.sirls.db;

import java.util.List;

import edu.arizona.sirls.beans.Term;

public class OtherTermDAO extends AbstractDAO {

	private static OtherTermDAO instance = null;
	
	private OtherTermDAO () throws Exception { }

	public static OtherTermDAO getInstance() throws Exception {
		if(instance == null)
			instance = new OtherTermDAO();
		return instance;
	}

	public void setOtherTerms(List<Term> otherTerms, String tablePrefix) throws Exception {
		this.openConnection();
		
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_possibleOtherTerms";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE " + tablePrefix + "_possibleOtherTerms ( " +
				" `term` varchar(100) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_possibleOtherTerms WRITE";
		this.executeSQL(sql).close();
		
		for(Term term : otherTerms) {
			sql = "INSERT INTO " + tablePrefix + "_possibleOtherTerms" + " VALUES ('" +
					term.getTerm() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();

		this.closeConnection();
	}
}
