package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

/**
 * This class represents the articles from the computer science vehicles 
 * @author daniel
 *
 */
@Entity(name="article")
public class ArticleEntity implements Serializable 
{

	private long id;
	
	private String title;

	private String abztract;
	
	private List<String> authors;
	
	private String downloadLink;
	
	private String eventInformation;
	
	private String remoteLocation;
	
	/**
	 * The evaluations made for the article
	 */
	private List<EvaluationEntity> evaluations;

	public ArticleEntity(){}
	
	public ArticleEntity(Article article)
	{
		evaluations = new ArrayList<EvaluationEntity>();
		this.abztract = article.getAbztract();
		this.authors = article.getAuthors();
		this.downloadLink = article.getDownloadLink();
		this.eventInformation = article.getEventInformation();
		this.title = article.getTitle();
		this.remoteLocation = article.getRemoteLocation();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemoteLocation() {
		return remoteLocation;
	}

	public void setRemoteLocation(String remoteLocation) {
		this.remoteLocation = remoteLocation;
	}
	
	public String generateFileName(){
		return "IEEE_-_ARTICLENAME" +  System.currentTimeMillis() + ".pdf";
	}

	@OneToMany(mappedBy="article",cascade=CascadeType.ALL)
	public List<EvaluationEntity> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationEntity> evaluations) {
		this.evaluations = evaluations;
	}
	
	public String getAbztract() {
		return abztract;
	}

	public void setAbztract(String abztract) {
		this.abztract = abztract;
	}

	@ElementCollection
	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getEventInformation() {
		return eventInformation;
	}

	public void setEventInformation(String eventInformation) {
		this.eventInformation = eventInformation;
	}	
	
	public Article convertToArticle()
	{
		Article article = new Article();
		article.setAbztract(this.getAbztract());
		article.setAuthors(this.getAuthors());
		article.setDownloadLink(this.getDownloadLink());
		article.setEventInformation(this.getEventInformation());
		article.setId(this.getId());
		article.setRemoteLocation(this.getRemoteLocation());
		article.setTitle(this.getTitle());
		return article;
	}
}
