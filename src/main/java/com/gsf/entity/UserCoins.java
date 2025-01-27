package com.gsf.entity;

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
	
	private double coins;
	
	boolean miningSessionOn;
	
	public UserCoins(String user, double coins, boolean miningSessionOn) {
		this.user=user;
		this.coins=coins;
		this.miningSessionOn=false;
	}
	
	
}
