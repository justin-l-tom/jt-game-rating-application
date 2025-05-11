package edu.pace.vgr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pace.vgr.dao.RatingRepository;
import edu.pace.vgr.entities.Rating;
import edu.pace.vgr.entities.UserAccount;

@Service
public class RatingService {
	
	@Autowired
	RatingRepository ratingRepo;
	
	public Rating save(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	public List<Rating> getAll() {
		return ratingRepo.findAll();
	}
	
	public Rating findByRatingId(long id) {		
		return ratingRepo.findByRatingId(id);		
	}
	
	public List<Rating> findByUser(UserAccount userAccount) {		
		return ratingRepo.findByUser(userAccount);		
	}

}
