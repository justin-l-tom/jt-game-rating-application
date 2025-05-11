package edu.pace.vgr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratings_seq")
	@SequenceGenerator(name = "ratings_seq", allocationSize = 1)
	private long ratingId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserAccount user;
	
	@ManyToOne
	@JoinColumn(name = "video_game_id", nullable = false)
	private VideoGame videoGame;
	
	private int score;
	
	private String reviewText;
	
	public Rating() {
		
	}

	public Rating(UserAccount user, VideoGame videoGame, int score, String reviewText) {
		super();
		this.user = user;
		this.videoGame = videoGame;
		this.score = score;
		this.reviewText = reviewText;
	}

	public long getRatingId() {
		return ratingId;
	}

	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
}
