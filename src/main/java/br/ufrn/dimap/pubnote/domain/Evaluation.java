package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the evaluations of an article
 * @author daniel
 *
 */

@Entity
public class Evaluation implements Serializable 
{
	
	private static final long serialVersionUID = 901427335996618360L;
	
	private int id_user;
	private int id_article;
	
	private String reviewerNotes;	

	private float originality, contribution, relevance, readability, relatedWorks, reviewerFamiliarity;
	private Date evalDate;
	
	
	private long id;
	private String notes;
	private int finalGrade;
	
	@Id
	@GeneratedValue
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_article() {
		return id_article;
	}
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	public String getNotes() 
	{
		return notes;
	}
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
	public int getFinalGrade() 
	{
		return finalGrade;
	}
	public void setFinalGrade(int finalGrade)
	{
		this.finalGrade = finalGrade;
	}
	
	public String getReviewerNotes() {
		return reviewerNotes;
	}


	public void setReviewerNotes(String reviewerNotes) {
		this.reviewerNotes = reviewerNotes;
	}
	
	public float getOriginality() {
		return originality;
	}


	public void setOriginality(float originality) {
		this.originality = originality;
	}


	public float getContribution() {
		return contribution;
	}


	public void setContribution(float contribution) {
		this.contribution = contribution;
	}


	public float getRelevance() {
		return relevance;
	}


	public void setRelevance(float relevance) {
		this.relevance = relevance;
	}


	public float getReadability() {
		return readability;
	}


	public void setReadability(float readability) {
		this.readability = readability;
	}


	public float getRelatedWorks() {
		return relatedWorks;
	}


	public void setRelatedWorks(float relatedWorks) {
		this.relatedWorks = relatedWorks;
	}


	public float getReviewerFamiliarity() {
		return reviewerFamiliarity;
	}


	public void setReviewerFamiliarity(float reviewerFamiliarity) {
		this.reviewerFamiliarity = reviewerFamiliarity;
	}


	public Date getEvalDate() {
		return evalDate;
	}


	public void setEvalDate(Date evalDate) {
		this.evalDate = evalDate;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Evaluation [originality=");
		builder.append(this.getOriginality());
		builder.append(", contribution=");
		builder.append(this.getContribution());
		builder.append(", relevance=");
		builder.append(this.getRelevance());
		
		builder.append(", readability=");
		builder.append(this.getReadability());
		builder.append(", relatedWorks=");
		builder.append(this.getRelatedWorks());
		builder.append(", relevance=");
		builder.append(this.getRelevance());
		builder.append(", id=");
		builder.append(this.getId());
		builder.append(", id_user=");
		builder.append(this.getId_user());
		builder.append(", id_article=");
		builder.append(this.getId_article());
		builder.append("]");
		return builder.toString();
	}
	
}
