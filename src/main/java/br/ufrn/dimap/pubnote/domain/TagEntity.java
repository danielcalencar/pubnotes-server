package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="tag")
public class TagEntity implements Serializable 
{

	private long id;
	private String description;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Tag convertToTag()
	{
		Tag tag = new Tag();
		tag.setDescription(this.getDescription());
		tag.setId(this.getId()); 
		return tag;
	}
}
