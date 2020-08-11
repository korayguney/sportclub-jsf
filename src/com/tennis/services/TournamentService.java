package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Login;
import com.tennis.models.Tournament;
import com.tennis.models.User;
import com.tennis.utils.HashAlgorithm;
import com.tennis.utils.HashingUtils;

@Stateless
public class TournamentService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Tournament> getAllTournaments() {
		List<Tournament> tournaments = entityManager.createQuery("from Tournament", Tournament.class).getResultList();
		return tournaments;
	}

	public void saveTournament(Tournament tournament) {

		entityManager.persist(tournament);

	}

	public void deleteTournament(Tournament tournament) {
		tournament = entityManager.find(Tournament.class, tournament.getId());
		entityManager.remove(tournament);
	}

	public Tournament getTournament(int tournamentId) {
		
		Tournament tournament = entityManager.find(Tournament.class, tournamentId);
		
		return tournament;
	}

}
