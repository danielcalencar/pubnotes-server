package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * This class represents the evaluations of an article
 * @author daniel
 *
 */

@Entity(name="Evaluation")
public class EvaluationEntity implements Serializable 
{
	
	private UserEntity user;
	private ArticleEntity article;
	private String reviewerNotes;	
	private float originality, contribution, relevance, readability, relatedWorks, reviewerFamiliarity;
	private Date evalDate;
	
	private long id;
	private boolean published;
	
	public EvaluationEntity() {
		super();
	}
	
	public EvaluationEntity(Evaluation evaluation) 
	{
		super();
		
		this.setContribution(evaluation.getContribution());
		this.setEvalDate(evaluation.getEvalDate());
		this.setOriginality(evaluation.getOriginality());
		this.setPublished(evaluation.getPublished());
		this.setReadability(evaluation.getReadability());
		this.setRelatedWorks(evaluation.getRelatedWorks());
		this.setRelevance(evaluation.getRelevance());
		this.setReviewerFamiliarity(evaluation.getReviewerFamiliarity());
		ArticleEntity articleEntity = new ArticleEntity();
		articleEntity.setId(evaluation.getArticle().getId());
		this.setArticle(articleEntity);
	}
	
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
	@ManyToOne
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
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
	
	@ManyToOne()
	public ArticleEntity getArticle()
	{
		return article;
	}
	
	public void setArticle(ArticleEntity article) 
	{
		this.article = article;
	}
	
	@Transient
	public Evaluation convertToEvaluation()
	{
		Evaluation eval = new Evaluation();
		eval.setContribution(this.getContribution());
		eval.setEvalDate(this.getEvalDate());
		eval.setId(this.getId());
		eval.setOriginality(this.getOriginality());
		eval.setReadability(this.getReadability());
		eval.setRelatedWorks(this.getRelatedWorks());
		eval.setRelevance(this.getRelevance());
		eval.setReviewerFamiliarity(this.getReviewerFamiliarity());
		eval.setReviewerNotes(this.getReviewerNotes());
		eval.setUser(this.getUser().convertToUser());
		eval.setPublished(this.getPublished());
		eval.setArticle(this.getArticle().convertToArticle());
		return eval;
	}
	
	public boolean getPublished() 
	{
		return published;
	}
	public void setPublished(boolean published) 
	{
		this.published = published;
	}
	
}
