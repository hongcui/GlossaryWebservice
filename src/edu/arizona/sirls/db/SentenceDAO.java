package edu.arizona.sirls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.arizona.sirls.beans.Sentence;

public class SentenceDAO extends AbstractDAO {

	private static SentenceDAO instance;
	
	private SentenceDAO() throws Exception { }
	
	public static SentenceDAO getInstance() throws Exception {
		if(instance == null)
			instance = new SentenceDAO();
		return instance;
	}
	
	public List<Sentence> getSentences(String tablePrefix) throws Exception {
		List<Sentence> result = new ArrayList<Sentence>();

		String sql = "SELECT * FROM " + tablePrefix + "_sentence";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while (resultSet.next())
			result.add(new Sentence(resultSet.getInt("sentid"), 
					resultSet.getString("source"), 
					resultSet.getString("sentence"),
					resultSet.getString("originalsent"), 
					resultSet.getString("lead"), 
					resultSet.getString("status"), 
					resultSet.getString("tag"), 
					resultSet.getString("modifier"), 
					resultSet.getString("charsegment")
					));
		preparedStatement.close();
		return result;
	}

	public void setSentences(List<Sentence> sentences, String tablePrefix) throws Exception {
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_sentence";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE " + tablePrefix + "_sentence (" +
				" `sentid` int(11) NOT NULL, " +
				" `source` varchar(500) DEFAULT NULL," +
				" `sentence` text," +
				" `originalsent` text," +
				" `lead` varchar(2000) DEFAULT NULL, " +
				" `status` varchar(20) DEFAULT NULL, " +
				" `tag` varchar(150) DEFAULT NULL," +
				" `modifier` varchar(150) DEFAULT NULL, " +
				" `charsegment` varchar(500) DEFAULT NULL, " +
				"  PRIMARY KEY (`sentid`), " +
				"  UNIQUE KEY `sentid` (`sentid`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_sentence WRITE";
		this.executeSQL(sql).close();
		
		for(Sentence sentence : sentences) {
			sql = "INSERT INTO " + tablePrefix + "_sentence" + " VALUES ('" +
					sentence.getSentId() + "','"  + 
					sentence.getSource() + "','" + 
					sentence.getSentence() + "','" + 
					sentence.getOriginalSentence() + "','" + 
					sentence.getLead() + "','" + 
					sentence.getStatus() + "','" + 
					sentence.getTag() + "','" + 
					sentence.getModifier() + "','" + 
					sentence.getCharacterSegment() + "')";	
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();
	}
}
