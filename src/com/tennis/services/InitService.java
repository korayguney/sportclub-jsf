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
import com.tennis.models.Period;
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

	
	List<Player> playerOfTheGames = new ArrayList<Player>();
	 
	
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
			
			Login login1 = new Login(player.getEmail(),player.getPassword(), Role.PLAYER);
			
			Player player2 = new Player();
			player2.setFirstname("Serena");
			player2.setLastname("Williams");
			player2.setAge(18);
			player2.setEmail("serena@gmail.com");
			player2.setGender(Gender.FEMALE);
			player2.setPhone_num("5555555555");
			player2.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			player2.setRole(Role.PLAYER);

			Login login4 = new Login(player2.getEmail(),player2.getPassword(), Role.PLAYER);

			Player player3 = new Player();
			player3.setFirstname("Roger");
			player3.setLastname("Federer");
			player3.setAge(47);
			player3.setEmail("roger@gmail.com");
			player3.setGender(Gender.MALE);
			player3.setPhone_num("5555555555");
			player3.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			player3.setRole(Role.PLAYER);
			
			Login login5 = new Login(player3.getEmail(),player3.getPassword(), Role.PLAYER);
			
			Player player4 = new Player();
			player4.setFirstname("Rafael");
			player4.setLastname("Nadal");
			player4.setAge(45);
			player4.setEmail("rafael@gmail.com");
			player4.setGender(Gender.MALE);
			player4.setPhone_num("5555555555");
			player4.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			player4.setRole(Role.PLAYER);

			Login login6 = new Login(player4.getEmail(),player4.getPassword(), Role.PLAYER);
			
			
			Parent parent = new Parent();
			parent.setFirstname("Serhan");
			parent.setLastname("Capar");
			parent.setPhone_num("000044443333222");
			parent.setAddress("London/England");
			parent.setEmail("s@s.com");
			parent.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			parent.setRole(Role.PARENT);
			parent.setChild_player(player);

			Login login2 = new Login(parent.getEmail(),parent.getPassword(), Role.PARENT);

			
			Admin admin = new Admin();
			admin.setFirstname("Koray");
			admin.setLastname("Guney");
			admin.setPhone_num("5305016681");
			admin.setEmail("k@k.com");
			admin.setPassword(HashingUtils.hashPassword("1234", HashAlgorithm.SHA256).toString());
			admin.setRole(Role.ADMIN);
			
			Login login3 = new Login(admin.getEmail(),admin.getPassword(), Role.ADMIN);

			
			entityManager.persist(player);
			entityManager.persist(player2);
			entityManager.persist(player3);
			entityManager.persist(player4);
			entityManager.persist(parent);
			entityManager.persist(admin);
			
		
			entityManager.persist(login1);
			entityManager.persist(login2);
			entityManager.persist(login3);
			entityManager.persist(login4);
			entityManager.persist(login5);
			entityManager.persist(login6);
			
			this.playerOfTheGames.add(player);
			this.playerOfTheGames.add(player2);
			
			
		}
	}
	
	public void saveInitilizedTournament() {
		
		List<Tournament> tournaments = entityManager.createQuery("from Tournament", Tournament.class).getResultList();

		if(tournaments.isEmpty()) {
			
			Tournament tournament1 = new Tournament();
			tournament1.setTour_name("Wimbledon");
			tournament1.setTour_place("London/ENGLAND");
			tournament1.setTour_start_date(LocalDate.of(2020, Month.NOVEMBER, 12));
			tournament1.setTour_finish_date(LocalDate.of(2020, Month.NOVEMBER, 25));
			
			Tournament tournament2 = new Tournament();
			tournament2.setTour_name("US Open");
			tournament2.setTour_place("USA");
			tournament2.setTour_start_date(LocalDate.of(2020, Month.DECEMBER, 3));
			tournament2.setTour_finish_date(LocalDate.of(2020, Month.DECEMBER, 12));
			
			entityManager.persist(tournament1);
			entityManager.persist(tournament2);
			
			Game game = new Game();
			game.setTournament(tournament1);
			game.setCourt(1);
			game.setDate(LocalDate.of(2020, Month.JUNE, 22));
			game.setTime(LocalTime.of(15, 30));
			game.setPlayer1((this.playerOfTheGames.get(0)));
			game.setPlayer2((this.playerOfTheGames.get(1)));
			
			entityManager.persist(game);
			
		}
		
		
		
	}

}
