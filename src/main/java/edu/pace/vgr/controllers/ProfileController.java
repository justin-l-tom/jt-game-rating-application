package edu.pace.vgr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pace.vgr.dto.Profile;
import edu.pace.vgr.entities.Rating;
import edu.pace.vgr.entities.UserAccount;
import edu.pace.vgr.entities.VideoGame;
import edu.pace.vgr.services.RatingService;
import edu.pace.vgr.services.UserAccountService;
import edu.pace.vgr.services.VideoGameService;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

	@Autowired
	UserAccountService accountService;
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	VideoGameService vgService;
	
	@GetMapping("/{id}")
	public String displayProfile(Model model, @PathVariable("id") Long id) {
		
		UserAccount userAccount = accountService.findByUserId(id);
		Profile profile = new Profile(userAccount.getUserId(), userAccount.getUserName());
		model.addAttribute("profile", profile);
		
		List<Rating> ratings = ratingService.findByUser(userAccount);
		model.addAttribute("ratings", ratings);
		
		List<VideoGame> videoGames = new ArrayList<>();
		
		for (Rating rating : ratings) {
			videoGames.add(rating.getVideoGame());
		}
		
		model.addAttribute("videoGames", videoGames);
		
		return "profiles/one-profile";
		
	}
	
	@GetMapping
	public String displayAllProfiles(Model model) {
		
		List<UserAccount> userAccounts = accountService.findAll();
		List<Profile> profiles = new ArrayList<>();
		
		for (UserAccount userAccount : userAccounts) {
			profiles.add(new Profile(userAccount.getUserId(), userAccount.getUserName()));
		}
		
		model.addAttribute("profiles", profiles);
		
		return "profiles/list-profiles";
		
	}
	
}
