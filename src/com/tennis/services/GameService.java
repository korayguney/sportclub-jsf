package com.tennis.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Match;
import com.tennis.models.MatchScore;
import com.tennis.models.Tournament;

@Stateless
public class GameService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Match> getAllGames(Tournament tournament) {
		List<Match> games = entityManager
				.createQuery("select g from Game g where g.tournament.tour_name =:tournament", Match.class)
				.setParameter("tournament", tournament.getTour_name()).getResultList();

		return games;
	}

	public void deleteGame(Match game) {

		game = entityManager.find(Match.class, game.getId());

		// game.setTournament(null);

		entityManager.remove(game);
	}

	public Match getGame(int gameId) {
		Match game = entityManager.find(Match.class, gameId);
		return game;
	}

	public void updateGame(Match game) {
		entityManager.merge(game);
	}

	public void saveGame(Match game) {
		System.out.println("SERVICE saveGame --> " + game);

		entityManager.persist(game);
	}

	public void submitScore(MatchScore gameSet) {
		System.out.println(gameSet);

		MatchScore gameSet2 = entityManager.find(MatchScore.class, gameSet.getId());

		if (gameSet2 != null) {

			entityManager.createQuery(
					"update GameSet g set g.score1 =:score1, g.score2 =:score2, g.set_number =:set_number where g.game =:game")
					.setParameter("score1", gameSet.getScore1()).setParameter("score2", gameSet.getScore2())
					.setParameter("game", gameSet.getGame()).setParameter("set_number", gameSet.getSet_number())
					.executeUpdate();

		} else {
			entityManager.persist(gameSet);
		}

	}

	public void submitSet(MatchScore set) {
		entityManager.merge(set);
	}

	public void startGame(Match game) {
		entityManager.merge(game);
	}

	public void finishGame(Match game) {
		entityManager.merge(game);
	}

	public List<Integer> getTotalScore(Match game) {
		List<MatchScore> gameSetList = entityManager.createQuery("from GameSet g where g.game =?1", MatchScore.class)
				.setParameter(1, game).getResultList();
		List<Integer> lastScore = new ArrayList<Integer>();

		return null;
	}

	public List<Integer> getLastSetScores(Match game) {
		Match found_game = entityManager.find(Match.class, game.getId());
		
		System.out.println("Found Game : " + found_game);
		
		List<Integer> scoreList = new ArrayList<>();
		scoreList.add(found_game.getSet_score1());
		scoreList.add(found_game.getSet_score2());
		
		System.out.println("Score is found : " + scoreList.get(0)+ "-" + scoreList.get(1));
		
		return scoreList;
	}

	public void submitSetScore(Match game) {
		entityManager.merge(game);
	}

	
}
