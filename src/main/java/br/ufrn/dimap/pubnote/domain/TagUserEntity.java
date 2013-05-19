package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity(name="tag_user")
public class TagUserEntity implements Serializable {
	
	private long id;
	private UserEntity user;
	private TagEntity tag;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	@ManyToOne 
	public TagEntity getTag() {
		return tag;
	}
	public void setTag(TagEntity tag) {
		this.tag = tag;
	}
	
	@Transient
	public TagUser convertToTagUser(){
		TagUser tu = new TagUser();
		tu.setId(this.getId());
		tu.setTag(this.getTag().convertToTag());
		tu.setUser(this.getUser().convertToUser());
	
		return tu;
	}
}
