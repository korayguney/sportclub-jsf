package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Game;
import com.tennis.models.Login;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.models.User;
import com.tennis.utils.HashAlgorithm;
import com.tennis.utils.HashingUtils;

@Stateless
public class GameService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Game> getAllGames(Tournament tournament) {
		List<Game> games = entityManager.createQuery("from Game g where g.tournament.tour_name =:tourname", Game.class)
				.setParameter("tourname", tournament.getTour_name()).getResultList();
		return games;
	}

	public void saveGame(Game game) {

		entityManager.persist(game);

	}

	public void deleteGame(Game game) {
		game = entityManager.find(Game.class, game.getId());
		entityManager.remove(game);
	}

	public Player findPlayer(int playerId) {
		return entityManager.find(Player.class, playerId);
	}

}
