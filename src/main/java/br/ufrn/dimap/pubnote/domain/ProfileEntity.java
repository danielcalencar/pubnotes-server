package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="profile")
public class ProfileEntity implements Serializable {

	private long id;
	//basic information
	private String institution;
	private String degree;
	private String location;
	private String gender;
	//personal information
	private String birthday;
	private String aboutme;
	//contact information
	private String facebook;
	private String email;
	private String phone;
	
	
	public ProfileEntity() {
		super();
	}
	
	public ProfileEntity(Profile profile) {
		super();
		
		this.setId(profile.getId());
		this.setAboutme(profile.getAboutme());
		this.setBirthday(profile.getBirthday());
		this.setDegree(profile.getDegree());
		this.setEmail(profile.getEmail());
		this.setFacebook(profile.getFacebook());
		this.setGender(profile.getGender());
		this.setInstitution(profile.getInstitution());
		this.setLocation(profile.getLocation());
		this.setPhone(profile.getPhone());
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAboutme() {
		return aboutme;
	}
	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Transient
	public Profile convertToProfile(){
		Profile p = new Profile();
		p.setId(this.getId());
		p.setAboutme(this.getAboutme());
		p.setBirthday(this.getBirthday());
		p.setDegree(this.getDegree());
		p.setEmail(this.getEmail());
		p.setFacebook(this.getFacebook());
		p.setGender(this.getGender());
		p.setInstitution(this.getInstitution());
		p.setLocation(this.getLocation());
		p.setPhone(this.getPhone());
		
		return p;
	}
	
}
