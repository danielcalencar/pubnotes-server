package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name="pubnotes_user")

public class UserEntity implements Serializable{
	
	private long id;
	
	private String username;
	
	private String useremail;
	
	private String password;
	
	private boolean onsigned;
	
	private List<UserEntity> friends;
	
	private List<TagEntity> tags;
	
	private List<TagUserEntity> markedTags;
	
	private ProfileEntity userprofile;
		
	public UserEntity(){
		super();
	}
	
	public UserEntity(User user){
		this.setUsername(user.getUsername());
		this.setUseremail(user.getUseremail());
		this.setPassword(user.getPassword());
		this.setId(user.getId());
		this.setOnsigned(user.isOnsigned());
		this.friends = new ArrayList<UserEntity>();
		this.tags = new ArrayList<TagEntity>();
		this.markedTags = new ArrayList<TagUserEntity>();
	}
	
	public boolean getOnsigned() {
		return onsigned;
	}
	public void setOnsigned(boolean onSigned) {
		this.onsigned = onSigned;
	}

	@OneToMany
	public List<TagUserEntity> getMarkedTags() {
		return markedTags;
	}

	public void setMarkedTags(List<TagUserEntity> markedTags) {
		this.markedTags = markedTags;
	}
	
	@ManyToMany
	public List<UserEntity> getFriends() {
		return friends;
	}
	public void setFriends(List<UserEntity> friends) {
		this.friends = friends;
	}
	
	@OneToMany
	public List<TagEntity> getTags() {
		return tags;
	}
	public void setTags(List<TagEntity> tags) {
		this.tags = tags;
	}
	
	@ManyToOne
	public ProfileEntity getUserprofile() {
		return userprofile;
	}
	public void setUserprofile(ProfileEntity userprofile) {
		this.userprofile = userprofile;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String email) {
		this.useremail = email;
	}
	
	@Transient
	public User convertToUser()
	{
		User user = new User();
		user.setUseremail(this.getUseremail());
		user.setId(this.getId());
		user.setPassword(this.getPassword());
		user.setUsername(this.getUsername());
		user.setOnsigned(this.getOnsigned());
		
		/*List<User> friends = new ArrayList<User>();
		if(this.getFriends() != null)
		{
			for(UserEntity fe : this.getFriends())
			{
				friends.add(fe.convertToUser());
			}
		}
		user.setFriends(friends);
		
		List<Tag> tags = new ArrayList<Tag>();
		if(this.getTags() != null)
		{
			for(TagEntity te : this.getTags())
			{
				Tag tag = te.convertToTag();
				tags.add(tag);
			}
		}
		user.setTags(tags);
		*/
		user.setUserprofile(this.getUserprofile().convertToProfile());
		return user;
	}
}
