package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Admin;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.Player.Gender;

@Stateless
public class InitService {

	@PersistenceContext
	EntityManager entityManager;

	public void saveInitilizedUser() {

		List<Player> players = entityManager.createQuery("from Player", Player.class).getResultList();
		
		System.out.println("Size of the players into database : " + players.size());
		
		if (players.size() == 0) {
			Player player = new Player();
			player.setFirstname("Yasmin");
			player.setLastname("Capar");
			player.setAge(17);
			player.setEmail("yasmin@gmail.com");
			player.setGender(Gender.FEMALE);
			player.setPhone_num(5555555555L);

			Parent parent = new Parent();
			parent.setFirstname("Serhan");
			parent.setLastname("Capar");
			parent.setPhone_num(000044443333222L);
			parent.setAddress("London/England");
			parent.setEmail("s@s.com");

			Admin admin = new Admin();
			admin.setFirstname("Koray");
			admin.setLastname("Guney");
			admin.setPhone_num(5305016681L);
			admin.setEmail("k@k.com");

			entityManager.persist(player);
			entityManager.persist(parent);
			entityManager.persist(admin);

		}
	}

}
