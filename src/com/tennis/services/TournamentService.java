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

	private void saveToLogin(User user) {
		Login login = new Login(user.getEmail(),
				HashingUtils.hashPassword(user.getPassword(), HashAlgorithm.SHA256).toString(), user.getRole());
		entityManager.persist(login);
	}

	private List<User> checkUserExists(String email) throws EmailIsAlreadyExistException {
		List<User> checkedResult = entityManager
				.createQuery("select e from User e where e.email =:emailOfUser ", User.class)
				.setParameter("emailOfUser", email).getResultList();

		return checkedResult;
	}

	public void deleteTournament(Tournament tournament) {
		tournament = entityManager.find(Tournament.class, tournament.getId());
		entityManager.remove(tournament);
	}

}
