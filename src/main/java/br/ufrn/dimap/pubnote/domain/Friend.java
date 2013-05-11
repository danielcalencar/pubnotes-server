package br.ufrn.dimap.pubnote.domain;

import java.io.Serializable;

public class Friend extends User implements Serializable 
{
	private Tag tag;

	public Tag getTag() 
	{
		return tag;
	}

	public void setTag(Tag tag) 
	{
		this.tag = tag;
	}
}
