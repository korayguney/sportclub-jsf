package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.Tournament;

@Stateless
public class TournamentService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Tournament> getAllTournaments() {
		List<Tournament> tournaments = entityManager.createQuery("select t from Tournament t", Tournament.class)
				.getResultList();

		return tournaments;
	}

	public void deleteTournament(Tournament tournament) {

		tournament = entityManager.find(Tournament.class, tournament.getId());
		entityManager.remove(tournament);
	}

	public Tournament getTournament(int tournamentId) {
		Tournament tournament = entityManager.find(Tournament.class, tournamentId);
		return tournament;
	}

	public void updateTournament(Tournament tournament) {
		entityManager.merge(tournament);
	}

	public boolean saveTournament(Tournament tournament) {
		
		tournament = entityManager.merge(tournament);
		return (tournament != null) ? true:false;
	}

}
