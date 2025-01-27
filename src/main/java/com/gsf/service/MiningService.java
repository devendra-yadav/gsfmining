package com.gsf.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gsf.entity.UserCoins;
import com.gsf.repository.UserCoinsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MiningService {
	
	@Autowired
	private UserCoinsRepository userCoinsRepository;
	
	@Value("${gsf.mining.interval}")
	private long miningInterval;
	
	@Value("${gsf.mining.duration}")
	private long miningDuration;
	
	@Value("${gsf.mining.rate}")
	private double miningRate;
	
	
	@Async
	public void start(String user) {
		
		if(isMiningSessionOn(user)) {
			log.info("Mining session is already on for the user "+user+" !!!!!!!!!!");
			return;
		}else {
			setMiningSessionFlag(user, true);
		}
		
		long currentTime = System.currentTimeMillis();
		long endTime = currentTime + miningDuration;
		
		UserCoins userCoins = userCoinsRepository.getByUser(user);
		double currentCoins = userCoins.getCoins();
		log.info(">>>> Mining Session started for "+user+". Current coins : "+currentCoins);
		while(currentTime < endTime) {
			currentCoins+=miningRate;
			log.info(user+" mining!!! current coins "+currentCoins);
			try {
				Thread.sleep(miningInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			currentTime = System.currentTimeMillis();
		}
		
		userCoins.setCoins(currentCoins);
		userCoins.setMiningSessionOn(false);
		
		userCoinsRepository.save(userCoins);
		log.info(">>>> Mining session ended for "+user+". Total coins : "+userCoins.getCoins());
		
	}

	
	private void setMiningSessionFlag(String user, boolean flag) {
		UserCoins userCoins = userCoinsRepository.getByUser(user);
		userCoins.setMiningSessionOn(flag);
		userCoinsRepository.saveAndFlush(userCoins);
	}
	
	private boolean isMiningSessionOn(String user) {
		UserCoins userCoins = userCoinsRepository.getByUser(user);
		log.info(user+" : "+userCoins);
		if(userCoins==null) {
			userCoins=new UserCoins(user, 0.0, false);
			userCoinsRepository.saveAndFlush(userCoins);
		}
		log.info("!!!!! "+userCoins);
		return userCoins.isMiningSessionOn();
	}

}
