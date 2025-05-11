package edu.pace.vgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.pace.vgr.entities.Rating;
import edu.pace.vgr.entities.UserAccount;
import edu.pace.vgr.entities.VideoGame;
import edu.pace.vgr.services.RatingService;
import edu.pace.vgr.services.UserAccountService;
import edu.pace.vgr.services.VideoGameService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	VideoGameService vgService;
	
	@Autowired
	UserAccountService accountService;
	
	@GetMapping("/new")
	public String displayRatingForm(@RequestParam("videoGameId") long videoGameId, Model model) {
		
		VideoGame videoGame = vgService.findByVideoGameId(videoGameId);
		UserAccount user = accountService.getLoggedInUser();
		
		Rating rating = new Rating(user, videoGame, 1, "");
		model.addAttribute("rating", rating);
		model.addAttribute("videoGame", videoGame);
		
		return "ratings/new-rating";
		
	}
	
	@PostMapping("/save")
	public String createRating(Model model, @Valid Rating rating, Errors errors) {
		
		if (errors.hasErrors()) {			
			return "ratings/new-rating";		
		}
		
		ratingService.save(rating);
		
		return "redirect:/videogames";
		
	}

}
