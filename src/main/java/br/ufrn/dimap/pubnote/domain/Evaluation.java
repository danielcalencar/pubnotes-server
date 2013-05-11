package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the evaluations of an article
 * @author daniel
 *
 */

public class Evaluation implements Serializable 
{
	
	private User user;	
	
	private Article article;
	
	private String reviewerNotes;	

	private float originality, contribution, relevance, readability, relatedWorks, reviewerFamiliarity;
	private Date evalDate;
	
	private boolean published;
	
	public boolean getPublished() 
	{
		return published;
	}
	
	public void setPublished(boolean published) 
	{
		this.published = published;
	}

	private long id;
	
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
		builder.append(", user=");
		builder.append(this.user.getId());
		//builder.append(", article=");
		//builder.append(this.article_id);
		builder.append("]");
		return builder.toString();
	}
	
}
