package com.tennis.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Game;
import com.tennis.models.GameSet;
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

	public void submitScore(GameSet gameSet) {
		System.out.println(gameSet);
		entityManager.createQuery("update GameSet g set g.score1 =:score1, g.score2 =:score2, g.set_number =:set_number where g.game =:game")
		.setParameter("score1", gameSet.getScore1()).setParameter("score2", gameSet.getScore2()).setParameter("game", gameSet.getGame())
		.setParameter("set_number", gameSet.getSet_number())
		.executeUpdate();
		
	}

	public void submitSet(GameSet set) {
		entityManager.merge(set);
	}

	public void startGame(Game game) {
		entityManager.merge(game);
	}

	public void finishGame(Game game) {
		entityManager.merge(game);
	}

	public List<Integer> getTotalScore(Game game) {
		List<GameSet> gameSetList = entityManager.createQuery("from GameSet g where g.game =?1", GameSet.class).setParameter(1, game).getResultList();
		List<Integer> lastScore = new ArrayList<Integer>();
		
		return null;
	}
}
