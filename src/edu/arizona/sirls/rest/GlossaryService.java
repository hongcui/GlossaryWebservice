package edu.arizona.sirls.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.arizona.sirls.beans.Sentence;
import edu.arizona.sirls.beans.Term;
import edu.arizona.sirls.beans.TermCategory;
import edu.arizona.sirls.beans.TermSynonym;
import edu.arizona.sirls.beans.WordRole;
import edu.arizona.sirls.db.CharacterDAO;
import edu.arizona.sirls.db.OtherTermDAO;
import edu.arizona.sirls.db.SentenceDAO;
import edu.arizona.sirls.db.StructureDAO;
import edu.arizona.sirls.db.TermCategoryDAO;
import edu.arizona.sirls.db.TermSynonymDAO;
import edu.arizona.sirls.db.WordRoleDAO;

@Path("/glossary")
public class GlossaryService {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;		
	
	private Logger logger;
	
	public GlossaryService() {
		logger =  LoggerFactory.getLogger(this.getClass());
	}
	
	@Path("/possibleStructure")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setPossibleStructure(@QueryParam("tablePrefix") String tablePrefix, List<Term> structures) {
		try {
			StructureDAO.getInstance().setStructures(structures, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}	
	
	@Path("/possibleCharacter")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setPossibleCharacter(@QueryParam("tablePrefix") String tablePrefix, List<Term> characters) {
		try {
			CharacterDAO.getInstance().setCharacters(characters, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/possibleOtherTerm")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setPossibleOtherTerm(@QueryParam("tablePrefix") String tablePrefix, List<Term> otherTerms) {
		try {
			OtherTermDAO.getInstance().setOtherTerms(otherTerms, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/wordRole")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<WordRole> getWordRole(@QueryParam("tablePrefix") String tablePrefix) {
		List<WordRole> result = new ArrayList<WordRole>();
		try {
			result = WordRoleDAO.getInstance().getWordRoles(tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Path("/wordRole")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setWordRole(@QueryParam("tablePrefix") String tablePrefix, List<WordRole> wordRoles) {
		try {
			WordRoleDAO.getInstance().setWordRoles(wordRoles, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/termCategory")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TermCategory> getTermCategories(@QueryParam("tablePrefix") String tablePrefix) {
		List<TermCategory> result = new ArrayList<TermCategory>();
		try {
			result = TermCategoryDAO.getInstance().getTermCategories(tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Path("/termCategory")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setTermCategory(@QueryParam("tablePrefix") String tablePrefix, List<TermCategory> termCategories) {
		try {
			TermCategoryDAO.getInstance().setTermCategories(termCategories, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/termSynonym")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TermSynonym> getTermSynonyms(@QueryParam("tablePrefix") String tablePrefix) {
		List<TermSynonym> result = new ArrayList<TermSynonym>();
		try {
			result = TermSynonymDAO.getInstance().getTermSynonyms(tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	@Path("/termSynonym")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public void getTermSynonyms(@QueryParam("tablePrefix") String tablePrefix, List<TermSynonym> termSynonyms) {
		try {
			TermSynonymDAO.getInstance().setTermSynonyms(termSynonyms, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/sentence")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSentences(@QueryParam("tablePrefix") String tablePrefix, List<Sentence> sentences) {
		try {
			SentenceDAO.getInstance().setSentences(sentences, tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Path("/sentence")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Sentence> getSentences(@QueryParam("tablePrefix") String tablePrefix) {
		List<Sentence> result = new ArrayList<Sentence>();
		try {
			result = SentenceDAO.getInstance().getSentences(tablePrefix);
		} catch (Exception e) {
			logger.error("Exception " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
}