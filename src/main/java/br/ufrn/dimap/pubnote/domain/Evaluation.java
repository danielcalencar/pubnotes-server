package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the evaluations of an article
 * @author daniel
 *
 */

@Entity
public class Evaluation implements Serializable 
{
	
	private static final long serialVersionUID = 901427335996618360L;
	
	private long id;
	private String notes;
	private int finalGrade;
	
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
	public String getNotes() 
	{
		return notes;
	}
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
	public int getFinalGrade() 
	{
		return finalGrade;
	}
	public void setFinalGrade(int finalGrade)
	{
		this.finalGrade = finalGrade;
	}
	
	
}
