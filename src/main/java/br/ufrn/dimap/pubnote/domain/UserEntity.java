package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="pubnotes_user")
public class UserEntity implements Serializable{
	

	private long id;
	
	private String username;
	
	private String password;
	
	private String email;

	@Id
	@GeneratedValue
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Transient
	public User convertToUser()
	{
		User user = new User();
		user.setUseremail(this.getEmail());
		user.setId(this.getId());
		user.setPassword(this.getPassword());
		user.setUsername(this.getUsername());
		return user;
	}
}
