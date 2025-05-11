package edu.pace.vgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.pace.vgr.dao.VideoGameRepository;
import edu.pace.vgr.entities.VideoGame;

@Service
public class VideoGameService {

	@Autowired
	VideoGameRepository vgRepo;

	public VideoGame save(VideoGame videoGame) {
		return vgRepo.save(videoGame);
	}

	public Page<VideoGame> getAll(Pageable pageable) {
		return vgRepo.findAll(pageable);
	}

	public VideoGame findByVideoGameId(long id) {
		return vgRepo.findByVideoGameId(id);
	}

	public Page<VideoGame> getByGenre(String genre, Pageable pageable) {
		return vgRepo.findByGenres(genre, pageable);
	}

	public List<String> getAllGenres() {
		return vgRepo.findAllGenresDistinct();
	}

	public Page<VideoGame> getByPlatform(String platform, Pageable pageable) {		
		return vgRepo.findByPlatforms(platform, pageable);		
	}

	public Page<VideoGame> getByGenreAndPlatform(String genre, String platform, Pageable pageable) {
		return vgRepo.findByGenresAndPlatforms(genre, platform, pageable);
	}

	public List<String> getAllPlatforms() {
		return List.of("Mobile", "Nintendo Switch", "PC", "PS4", "PS5", "Stadia", "Xbox One", "Xbox Series X");
	}

}
