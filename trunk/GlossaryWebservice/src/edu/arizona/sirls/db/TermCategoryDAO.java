package edu.arizona.sirls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.arizona.sirls.beans.TermCategory;

public class TermCategoryDAO  extends AbstractDAO {
	
	private TermCategoryDAO() throws Exception {
		super();
	}

	private static TermCategoryDAO instance;
	
	public static TermCategoryDAO getInstance() throws Exception {
		if(instance == null)
			instance = new TermCategoryDAO();
		return instance;
	}

	public List<TermCategory> getTermCategories(String tablePrefix) throws Exception {
		List<TermCategory> result = new ArrayList<TermCategory>();
		
		this.openConnection();
		
		String sql = "SELECT * FROM " + tablePrefix + "_term_category";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while (resultSet.next())
			result.add(new TermCategory(resultSet.getString("term"), resultSet.getString("category"), resultSet.getString("hasSyn")));

		this.closeConnection();
		
		return result;
	}

	public void setTermCategories(List<TermCategory> termCategories, String tablePrefix) throws Exception {
		this.openConnection();
		String sql = "DROP TABLE IF EXISTS " + tablePrefix + "_term_category";
		this.executeSQL(sql).close();
		
		sql = "CREATE TABLE " + tablePrefix + "_term_category ( " +
				" `term` varchar(100) DEFAULT NULL, " +
				" `category` varchar(200) DEFAULT NULL, " +
				" `hasSyn` tinyint(1) DEFAULT NULL" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		this.executeSQL(sql).close();
		
		sql = "LOCK TABLES " + tablePrefix + "_term_category WRITE";
		this.executeSQL(sql).close();
		
		for(TermCategory termCategory : termCategories) {
			sql = "INSERT INTO " + tablePrefix + "_term_category" + " VALUES ('" +
					termCategory.getTerm() + "','" +
					termCategory.getCategory() + "','" +
					termCategory.getHasSyn() + "')";
			this.executeSQL(sql).close();
		}
		
		sql = "UNLOCK TABLES;";
		this.executeSQL(sql).close();
			
		this.closeConnection();
	}
}
