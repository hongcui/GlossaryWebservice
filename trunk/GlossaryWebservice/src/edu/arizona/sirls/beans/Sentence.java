package edu.arizona.sirls.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sentence {

	private int sentId;
	private String source;
	private String sentence;
	private String originalSentence;
	private String lead;
	private String status;
	private String tag;
	private String modifier;
	private String characterSegment;
	
	public Sentence() { }
	
	public Sentence(int sentId, String source, String sentence,
			String originalSentence, String lead, String status, String tag,
			String modifier, String characterSegment) {
		this.sentId = sentId;
		this.source = source;
		this.sentence = sentence;
		this.originalSentence = originalSentence;
		this.lead = lead;
		this.status = status;
		this.tag = tag;
		this.modifier = modifier;
		this.characterSegment = characterSegment;
	}

	public int getSentId() {
		return sentId;
	}

	public void setSentId(int sentId) {
		this.sentId = sentId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getOriginalSentence() {
		return originalSentence;
	}

	public void setOriginalSentence(String originalSentence) {
		this.originalSentence = originalSentence;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getCharacterSegment() {
		return characterSegment;
	}

	public void setCharacterSegment(String characterSegment) {
		this.characterSegment = characterSegment;
	}
	
	
	
}
