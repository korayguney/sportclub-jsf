package com.tennis.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Player;
import com.tennis.models.Player.Gender;

@Stateless
public class InitService {

	
	@PersistenceContext
	EntityManager entityManager;
	
	
	public void saveInitilizedUser() {
		
		Player player = new Player();
		player.setFirstname("Yasmin");
		player.setLastname("Capar");
		player.setAge(17);
		player.setEmail("yasmin@gmail.com");
		player.setGender(Gender.FEMALE);
		player.setPhone_num(5555555555L);
		
		entityManager.persist(player);
		
		
		
		
	}
	
	
	
}
