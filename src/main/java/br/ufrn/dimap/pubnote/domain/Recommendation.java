package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.List;

public class Recommendation implements Serializable{

	private long idUsuario;
	private List<Long> idDestinatarios;
	private String obs;
	private String conference;
	private String link;
	private String title;
	private String authors;

	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Long> getIdDestinatarios() {
		return idDestinatarios;
	}
	public void setIdDestinatarios(List<Long> idDestinatarios) {
		this.idDestinatarios = idDestinatarios;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [Article=");
		builder.append(", user_remetente");
		builder.append(", user_destinatario");
		builder.append(", obs");
		builder.append(obs);
		builder.append("]");
		return builder.toString();
	}
}
