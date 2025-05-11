package edu.pace.vgr.dto;

import java.util.List;

import edu.pace.vgr.entities.Rating;

public class Profile {

	private long userId;
	private String userName;
	private List<Rating> ratings;

	public Profile(long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}   
    
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}
