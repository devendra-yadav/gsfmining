package com.gsf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoins {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String user;
	
	private double minedCoins;
	
	private boolean miningSessionOn;
	
	private LocalDateTime joiningDateTime = LocalDateTime.now();
	
	private String lastMiningDeviceIPAddress;
	
	private String lastMiningDevice;
	
	private String lastMiningDeviceOs;
	
	private String lastMiningUserAgent;
	
	private LocalDateTime lastMiningSession = LocalDateTime.now();
	
	public UserCoins(String user, double coins, boolean miningSessionOn) {
		this.user=user;
		this.minedCoins=coins;
		this.miningSessionOn=false;
	}
	
	
}
