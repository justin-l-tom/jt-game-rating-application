package edu.pace.vgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.pace.vgr.entities.VideoGame;
import edu.pace.vgr.services.VideoGameService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/videogames")
public class VideoGameController {

	@Autowired
	VideoGameService vgService;
	
	@GetMapping
	public String displayVideoGames(@RequestParam(required = false) String genre,
			@RequestParam(required = false) String platform,
			@RequestParam(defaultValue = "0") int page, Model model) {
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by("name"));
		Page<VideoGame> videoGames;
		
		if (has(genre) && has(platform)) {			
	        videoGames = vgService.getByGenreAndPlatform(genre, platform, pageable);	        
	    }

		else if (has(genre)) {			
	        videoGames = vgService.getByGenre(genre, pageable);	        
	    }

		else if (has(platform)) {			
	        videoGames = vgService.getByPlatform(platform, pageable);	        
	    }

		else {			
	        videoGames = vgService.getAll(pageable);	        
	    }
		
		model.addAttribute("videoGames", videoGames);
		model.addAttribute("selectedGenre", genre);
		model.addAttribute("selectedPlatform", platform);
		model.addAttribute("allGenres", vgService.getAllGenres());
		model.addAttribute("allPlatforms", vgService.getAllPlatforms());
		return "video-games/list-video-games";
		
	}
	
	private boolean has(String s) {		
		return s != null && !s.isEmpty();		
	}
	
	@GetMapping("/{id}")
	public String displayOneVideoGame(Model model, @PathVariable("id") Long id) {
		
		VideoGame videoGame = vgService.findByVideoGameId(id);
		model.addAttribute("videoGame", videoGame);
		
		return "video-games/one-video-game";
		
	}
	
	@GetMapping("/new")
	public String displayVideoGameForm(Model model) {
		
		VideoGame videoGame = new VideoGame();
		model.addAttribute("videoGame", videoGame);
		
		return "video-games/new-video-game";
		
	}
	
	@PostMapping("/save")
	public String createVideoGame(Model model, @Valid VideoGame videoGame, Errors errors) {
		
		if (errors.hasErrors()) {
			return "video-games/new-video-game";
		}
		
		vgService.save(videoGame);
		
		return "redirect:/videogames";
		
	}
	
}
