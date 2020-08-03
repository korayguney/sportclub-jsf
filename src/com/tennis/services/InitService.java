package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Admin;
import com.tennis.models.Login;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.Role;
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
			player.setPassword("1234");
			player.setRole(Role.PLAYER);
			
			Login login1 = new Login(player.getEmail(),player.getPassword(), Role.PLAYER);
			

			Parent parent = new Parent();
			parent.setFirstname("Serhan");
			parent.setLastname("Capar");
			parent.setPhone_num(000044443333222L);
			parent.setAddress("London/England");
			parent.setEmail("s@s.com");
			parent.setPassword("1234");
			parent.setRole(Role.PARENT);

			Login login2 = new Login(parent.getEmail(),parent.getPassword(), Role.PARENT);

			
			Admin admin = new Admin();
			admin.setFirstname("Koray");
			admin.setLastname("Guney");
			admin.setPhone_num(5305016681L);
			admin.setEmail("k@k.com");
			admin.setPassword("1234");
			admin.setRole(Role.ADMIN);
			
			Login login3 = new Login(admin.getEmail(),admin.getPassword(), Role.ADMIN);

			
			entityManager.persist(player);
			entityManager.persist(parent);
			entityManager.persist(admin);
			
			entityManager.persist(login1);
			entityManager.persist(login2);
			entityManager.persist(login3);

		}
	}

}
