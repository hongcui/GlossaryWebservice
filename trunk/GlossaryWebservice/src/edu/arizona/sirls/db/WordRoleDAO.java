package edu.arizona.sirls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.arizona.sirls.beans.WordRole;

public class WordRoleDAO extends AbstractDAO {

	private static WordRoleDAO instance;
	
	private WordRoleDAO() throws Exception { }
	
	public static WordRoleDAO getInstance() throws Exception {
		if(instance == null)
			instance = new WordRoleDAO();
		return instance;
	}

	public List<WordRole> getWordRoles(String tablePrefix) throws Exception {
		List<WordRole> result = new ArrayList<WordRole>();

		this.openConnection();
		
		String sql = "SELECT * FROM " + tablePrefix + "_wordroles";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while (resultSet.next())
			result.add(new WordRole(resultSet.getString("word"), resultSet.getString("semanticrole"), resultSet.getString("savedid")));
		
		this.closeConnection();
		return result;
	}

	public void setWordRoles(List<WordRole> wordRoles, String tablePrefix) throws Exception {
		this.openConnection();
		
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_wordroles";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "_wordroles (" +
				"`word` varchar(50) NOT NULL DEFAULT ''," +
				" `semanticrole` varchar(2) NOT NULL DEFAULT ''," +
				" `savedid` varchar(40) DEFAULT NULL," +
				" PRIMARY KEY (`word`,`semanticrole`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_wordroles WRITE";
		this.executeSQL(sql).close();
		
		for(WordRole wordRole : wordRoles) {
			sql = "INSERT INTO " + tablePrefix + "_wordroles" + " VALUES ('" +
					wordRole.getWord() + "','" +
					wordRole.getSemanticRole() + "','" +
					wordRole.getSavedid() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();
		
		this.closeConnection();
	}
}
