package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name="recommendation")
public class RecommendationEntity implements Serializable{

	@Id
	@GeneratedValue
	private long id;

	private static final long serialVersionUID = 7532253951286031634L;
	
	@ManyToOne
	private UserEntity user_remetente;
	
	@OneToMany
	private List<UserEntity> user_destinatarios;
	
	private String obs;
	private String title;
	private String conference;
	private String link;
	private String authors;
	
	public List<UserEntity> getUser_destinatarios() {
		return user_destinatarios;
	}

	public void setUser_destinatarios(List<UserEntity> user_destinatarios) {
		this.user_destinatarios = user_destinatarios;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RecommendationEntity(){
		super();
	}
	
	public RecommendationEntity(Recommendation recommendation){
		this.setObs(recommendation.getObs());
		//this.setUser_destinatario(recommendation.getUser_destinatario());
		//this.setUser_remetente(recommendation.getUser_remetente());
		this.setTitle(recommendation.getTitle());
		this.setAuthors(recommendation.getAuthors());
		this.setLink(recommendation.getLink());
		this.setConference(recommendation.getConference());
	}
	
	public UserEntity getUser_remetente() {
		return user_remetente;
	}

	public void setUser_remetente(UserEntity user_remetente) {
		this.user_remetente = user_remetente;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	@Transient
	public Recommendation convertToRecommendation()
	{
		Recommendation recommend = new Recommendation();
		recommend.setAuthors(this.getAuthors());
		recommend.setConference(this.getConference());
		//recommend.setIdDestinatarios(this.getUser_destinatarios());
		//recommend.setIdUsuario(idUsuario);
		recommend.setLink(this.getLink());
		recommend.setObs(this.getObs());
		recommend.setTitle(this.getTitle());
		
		return recommend;
	}
}
