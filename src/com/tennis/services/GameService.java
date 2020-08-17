package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Game;
import com.tennis.models.Period;
import com.tennis.models.Tournament;

@Stateless
public class GameService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Game> getAllGames(Tournament tournament) {
		List<Game> games = entityManager
				.createQuery("select g from Game g where g.tournament.tour_name =:tournament", Game.class)
				.setParameter("tournament", tournament.getTour_name()).getResultList();

		return games;
	}

	public void deleteGame(Game game) {

		game = entityManager.find(Game.class, game.getId());
		
		//game.setTournament(null);
		
		entityManager.remove(game);
	}

	public Game getGame(int gameId) {
		Game game = entityManager.find(Game.class, gameId);
		return game;
	}

	public void updateGame(Game game) {
		entityManager.merge(game);
	}

	public void saveGame(Game game) {
		System.out.println("SERVICE saveGame --> "+ game);

		entityManager.persist(game);
	}

	public void submitScore(Game game) {
		
		entityManager.merge(game);
	}

	public void submitPeriod(Period period) {
		entityManager.merge(period);
	}

}
