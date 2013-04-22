package edu.arizona.sirls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.arizona.sirls.beans.TermSynonym;

public class TermSynonymDAO extends AbstractDAO {

	private static TermSynonymDAO instance;

	private TermSynonymDAO() throws Exception { }

	public static TermSynonymDAO getInstance() throws Exception {
		if(instance == null)
			instance = new TermSynonymDAO();
		return instance;
	}

	public List<TermSynonym> getTermSynonyms(String tablePrefix) throws Exception {
		List<TermSynonym> result = new ArrayList<TermSynonym>();

		this.openConnection();
		
		String sql = "SELECT * FROM " + tablePrefix + "_syns";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while (resultSet.next()) 
			result.add(new TermSynonym(resultSet.getString("term"), resultSet.getString("synonym")));
		
		this.closeConnection();

		return result;
	}
	
	public void setTermSynonyms(List<TermSynonym> termSynonyms, String tablePrefix) throws Exception {
		this.openConnection();
		
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_syns";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE " + tablePrefix + "_syns ( " + 
				" `term` varchar(100) DEFAULT NULL, " + 
				" `synonym` varchar(100) DEFAULT NULL " + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_syns WRITE";
		this.executeSQL(sql).close();
		
		for(TermSynonym termSynonym : termSynonyms) {
			sql = "INSERT INTO " + tablePrefix + "_syns" + " VALUES ('" +
					termSynonym.getTerm() + "','" +
					termSynonym.getSynonym() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();
		
		this.closeConnection();
	}

}
