package br.ufrn.dimap.pubnote.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name="friend")
@DiscriminatorValue("F")
public class FriendEntity extends UserEntity 
{
	private TagEntity tag;

	@ManyToOne
	public TagEntity getTag()
	{
		return tag;
	}

	public void setTag(TagEntity tag)
	{
		this.tag = tag;
	}
	
	public Friend convertToFriend()
	{
		Friend friend = new Friend();
		friend.setId(this.getId());
		friend.setOnsigned(this.getOnsigned());
		friend.setPassword(this.getPassword());
		//friend.setTag(this.getTag().convertToTag());
		friend.setUseremail(this.getUseremail());
		friend.setUsername(this.getUsername());
		friend.setUserprofile(this.getUserprofile().convertToProfile());
		return friend;
	}
	
	public User convertToUser()
	{
		User friend = new User();
		friend.setId(this.getId());
		friend.setOnsigned(this.getOnsigned());
		friend.setPassword(this.getPassword());
		friend.setUseremail(this.getUseremail());
		friend.setUsername(this.getUsername());
		friend.setUserprofile(this.getUserprofile().convertToProfile());
		return friend;
	}
}
