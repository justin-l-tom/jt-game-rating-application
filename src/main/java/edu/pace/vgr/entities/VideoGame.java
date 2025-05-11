package edu.pace.vgr.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class VideoGame {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_game_seq")
	@SequenceGenerator(name = "video_game_seq", sequenceName = "video_game_seq", allocationSize = 1)
	private long videoGameId;

	@NotBlank(message = "* Must enter a video game name.")
	@Size(min = 1, max = 70, message = "* Name must be between {min} and {max}.")
	private String name;
	
	private List<String> genres;
	
	private List<String> platforms;
	
	@OneToMany(mappedBy = "videoGame", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;
	
	public VideoGame() {
		
	}
	
	public VideoGame(String name, String status, List<String> genres, List<String> platforms) {
		super();
		this.name = name;
		this.genres = genres;
		this.platforms = platforms;
	}

	public long getVideoGameId() {
		return videoGameId;
	}

	public void setVideoGameId(long videoGameId) {
		this.videoGameId = videoGameId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getGenres() {
		return genres;
	}
	
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
	public List<String> getPlatforms() {
		return platforms;
	}
	
	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}
	
}
