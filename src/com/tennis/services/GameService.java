package com.tennis.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Game;
import com.tennis.models.GameSet;
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

	public Game getGame(int gameId) {
		return entityManager.find(Game.class, gameId);
	}

	public void startGame(Game game) {
		entityManager.merge(game);
	}

	public void submitScore(GameSet gameSet) {
		
		GameSet gameSet2 = entityManager.find(GameSet.class, gameSet.getId());
		
		if(gameSet2 != null) {
			entityManager.createQuery("UPDATE GameSet g SET g.score1 =?1, g.score2 =?2, g.set_no =?3 WHERE g.game =?4 ")
			.setParameter(1, gameSet.getScore1()).setParameter(2, gameSet.getScore2()).setParameter(3, gameSet.getSet_no())
			.setParameter(4, gameSet.getGame()).executeUpdate();
		} else {
			entityManager.persist(gameSet);
		}
		
	}

	public List<Integer> getLastSetScores(Game game) {
		Game found_game = entityManager.find(Game.class, game.getId());
		
		List<Integer> scores = new ArrayList<Integer>();
		scores.add(found_game.getSet_score1());
		scores.add(found_game.getSet_score2());
		
		return scores;
	}

	public void submitSetScore(Game game) {
		entityManager.merge(game);
	}

}
