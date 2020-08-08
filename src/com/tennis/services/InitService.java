package com.tennis.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Admin;
import com.tennis.models.Game;
import com.tennis.models.Login;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.Role;
import com.tennis.models.Tournament;
import com.tennis.utils.HashAlgorithm;
import com.tennis.utils.HashingUtils;
import com.tennis.models.Player.Gender;

@Stateless
public class InitService {

	@PersistenceContext
	EntityManager entityManager;
	
	List<Player> playersOfTournament1 = new ArrayList<Player>();

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
			player.setPhone_num("5555555555");
			player.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			player.setRole(Role.PLAYER);

			Login login1 = new Login(player.getEmail(), player.getPassword(), Role.PLAYER);
			
			Player player2 = new Player();
			player2.setFirstname("Elif");
			player2.setLastname("Capar");
			player2.setAge(17);
			player2.setEmail("elif@gmail.com");
			player2.setGender(Gender.FEMALE);
			player2.setPhone_num("5555555555");
			player2.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			player2.setRole(Role.PLAYER);

			Login login4 = new Login(player2.getEmail(), player2.getPassword(), Role.PLAYER);

			Parent parent = new Parent();
			parent.setFirstname("Serhan");
			parent.setLastname("Capar");
			parent.setPhone_num("000044443333222");
			parent.setAddress("London/England");
			parent.setEmail("s@s.com");
			parent.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			parent.setRole(Role.PARENT);

			Login login2 = new Login(parent.getEmail(), parent.getPassword(), Role.PARENT);

			Admin admin = new Admin();
			admin.setFirstname("Koray");
			admin.setLastname("Guney");
			admin.setPhone_num("5305016681");
			admin.setEmail("k@k.com");
			admin.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			admin.setRole(Role.ADMIN);

			Login login3 = new Login(admin.getEmail(), admin.getPassword(), Role.ADMIN);

			entityManager.persist(player);
			entityManager.persist(player2);
			entityManager.persist(parent);
			entityManager.persist(admin);

			entityManager.persist(login1);
			entityManager.persist(login2);
			entityManager.persist(login3);
			entityManager.persist(login4);

			this.playersOfTournament1.add(player);
			this.playersOfTournament1.add(player2);
			
		}
	}

	public void saveInitilizedTournament() {
		List<Tournament> tournaments = entityManager.createQuery("from Tournament", Tournament.class).getResultList();
		if (tournaments.isEmpty()) {
			Tournament tournament1 = new Tournament();
			tournament1.setTour_name("Wimbledon");
			tournament1.setTour_place("London/England");
			tournament1.setTour_start_date(LocalDate.of(2020, Month.JUNE, 24));
			tournament1.setTour_finish_date(LocalDate.of(2020, Month.JULY, 3));

			Tournament tournament2 = new Tournament();
			tournament2.setTour_name("US Open");
			tournament2.setTour_place("San Francisco/USA");
			tournament2.setTour_start_date(LocalDate.of(2020, Month.APRIL, 12));
			tournament2.setTour_finish_date(LocalDate.of(2020, Month.APRIL, 23));

			entityManager.persist(tournament1);
			entityManager.persist(tournament2);
			
			Game game1 = new Game();
			game1.setTournament(tournament1);
			game1.setPlace(tournament1.getTour_place());
			game1.setCourt(1);
			game1.setDate(LocalDate.of(2020, Month.NOVEMBER, 13));
			game1.setTime(LocalTime.of(12, 30));
			game1.setPlayersOfGame(this.playersOfTournament1);
			
			entityManager.persist(game1);
						
		}

	}


}
