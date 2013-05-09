package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name="pubnotes_user")
public class UserEntity implements Serializable{
	
	@Id()
	private long id;
	
	private String username;
	
	private String useremail;
	
	private String password;
	
	private boolean onsigned;
	
	private List<String> friends;
	
	private List<String> tags;
	
	private HashMap<String, List<String>> tagToUsers;

	private ProfileEntity userprofile;

		
	public UserEntity(){
		super();
	}
	public UserEntity(User user){
		this.setUsername(user.getUsername());
		this.setUseremail(user.getUseremail());
		this.setPassword(user.getPassword());
		this.setId(user.getId());
		this.setOnSigned(user.isOnsigned());
		this.setFriends(user.getFriends());
		this.setTags(user.getTags());
		this.setTagToUsers(user.getTagToUsers());
		ProfileEntity entity = new ProfileEntity();
		entity.setId(user.getUserprofile().getId());
		this.setUserprofile(entity);
	}
	
	public boolean isOnSigned() {
		return onsigned;
	}
	public void setOnSigned(boolean onSigned) {
		this.onsigned = onSigned;
	}
	
	@ElementCollection
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	@ElementCollection
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	//DUVIDA
	public HashMap<String, List<String>> getTagToUsers() {
		return tagToUsers;
	}
	public void setTagToUsers(HashMap<String, List<String>> tagToUsers) {
		this.tagToUsers = tagToUsers;
	}
	
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
		user.setID(this.getId());
		user.setPassword(this.getPassword());
		user.setUsername(this.getUsername());
		user.setOnsigned(this.isOnSigned());
		user.setFriends(this.getFriends());
		user.setTags(this.getTags());
		user.setTagToUsers(this.getTagToUsers());
		user.setUserprofile(this.getUserprofile().convertToProfile());
		return user;
	}
}
