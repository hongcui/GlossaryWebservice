package edu.arizona.sirls.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TermCategory {

	private String term;
	private String category;
	private String hasSyn;
	
	public TermCategory() { }
	
	public TermCategory(String term, String category, String hasSyn) {
		this.term = term;
		this.category = category;
		this.hasSyn = hasSyn;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHasSyn() {
		return hasSyn;
	}

	public void setHasSyn(String hasSyn) {
		this.hasSyn = hasSyn;
	}
	
	
}
