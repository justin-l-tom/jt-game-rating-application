package edu.pace.vgr.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import edu.pace.vgr.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

	public Optional<UserAccount> findByUserId(long id);

	public Optional<UserAccount> findByUserName(String userName);
	
	@Override
	public List<UserAccount> findAll();
	
}
