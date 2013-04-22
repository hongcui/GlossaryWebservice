package edu.arizona.sirls.db;

import java.util.List;

import edu.arizona.sirls.beans.Term;

public class StructureDAO extends AbstractDAO {

	private static StructureDAO instance = null;
	
	private StructureDAO () throws Exception { }

	public static StructureDAO getInstance() throws Exception {
		if(instance == null)
			instance = new StructureDAO();
		return instance;
	}

	public void setStructures(List<Term> structures, String tablePrefix) throws Exception {
		this.openConnection();
		
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_possibleStructures";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE " + tablePrefix + "_possibleStructures ( " +
				" `term` varchar(100) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_possibleStructures WRITE";
		this.executeSQL(sql).close();
		
		for(Term term : structures) {
			sql = "INSERT INTO " + tablePrefix + "_possibleStructures" + " VALUES ('" +
					term.getTerm() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();

		this.closeConnection();
	}

}
