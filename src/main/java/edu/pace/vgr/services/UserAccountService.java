package edu.pace.vgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.pace.vgr.dao.UserAccountRepository;
import edu.pace.vgr.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository accountRepo;

	public UserAccount save(UserAccount user) {
		return accountRepo.save(user);		
	}
	
	public UserAccount findByUserId(long id) {

		return accountRepo.findByUserId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

	}
	
	public UserAccount findByUserName(String userName) {
		
		return accountRepo.findByUserName(userName)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
		
	}

    public UserAccount getLoggedInUser() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return accountRepo.findByUserName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        
    }

	public List<UserAccount> findAll() {
		return accountRepo.findAll();
	}

}
