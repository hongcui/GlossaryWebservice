package edu.arizona.sirls.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Term {

	private String term;
	
	public Term() { }

	public Term(String term) {
		this.term = term;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
