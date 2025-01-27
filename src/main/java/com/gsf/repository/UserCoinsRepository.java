package com.gsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsf.entity.UserCoins;

@Repository
public interface UserCoinsRepository extends JpaRepository<UserCoins, Long> {
	
	public UserCoins getByUser(String user);

}
