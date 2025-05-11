package edu.pace.vgr.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.pace.vgr.entities.Rating;
import edu.pace.vgr.entities.UserAccount;

public interface RatingRepository extends CrudRepository<Rating, Long> {

	@Override
	public List<Rating> findAll();

	public Rating findByRatingId(long id);
	
	public List<Rating> findByUser(UserAccount userAccount);
	
}
