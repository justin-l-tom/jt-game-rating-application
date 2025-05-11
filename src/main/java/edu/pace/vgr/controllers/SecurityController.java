package edu.pace.vgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pace.vgr.entities.UserAccount;
import edu.pace.vgr.services.UserAccountService;

@Controller
public class SecurityController {

	@Autowired
	UserAccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
		
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountService.save(user);
		return "redirect:/videogames";
		
	}
	
}
