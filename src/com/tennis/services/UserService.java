package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Match;
import com.tennis.models.Match.GameStatus;
import com.tennis.models.MatchScore;
import com.tennis.models.Login;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.models.User;
import com.tennis.utils.HashAlgorithm;
import com.tennis.utils.HashingUtils;

@Stateless
public class UserService {

	@PersistenceContext
	EntityManager entityManager;

	public List<User> getAllUsers() {
		List<User> users = entityManager.createQuery("from User", User.class).getResultList();
		return users;
	}

	public void saveUser(User user) throws EmailIsAlreadyExistException {

		List<User> result = checkUserExists(user.getEmail());

		if(result.size() > 0) {
			throw new EmailIsAlreadyExistException();
		}
		
		entityManager.persist(user);
		
		saveToLogin(user);

	}

	private void saveToLogin(User user) {
		Login login = new Login(user.getEmail(), HashingUtils.hashPassword(user.getPassword(), HashAlgorithm.SHA256).toString(), user.getRole());
		entityManager.persist(login);
	}

	private List<User> checkUserExists(String email) throws EmailIsAlreadyExistException {
		List<User> checkedResult = entityManager
				.createQuery("select e from User e where e.email =:emailOfUser ", User.class)
				.setParameter("emailOfUser", email).getResultList();

		return checkedResult;
	}

	public void deleteUser(User user) {
		user = entityManager.find(User.class, user.getId());
		entityManager.remove(user);
		entityManager.createQuery("delete from Login l where l.email =:email")
        .setParameter("email",  user.getEmail()).executeUpdate();
	}
	
	public User getUser(int userId) {
		User user = entityManager.find(User.class, userId);
		return user;
	}

	public void updateUser(User user, String email) {
		Login login = entityManager.createQuery("from Login l where l.email =:email", Login.class).setParameter("email", email).getSingleResult();
		login.setEmail(email);
		entityManager.merge(login);
		entityManager.merge(user);
	}

	public List<Player> getAllPlayers() {
		List<Player> players = entityManager.createQuery("from Player p", Player.class).getResultList();
		return players;
	}

	public Player getPlayer(int player1id) {
		return entityManager.find(Player.class, player1id);
	}

	public Player getPlayerOfParent(User user) {
		Parent parent = entityManager.createQuery("from Parent p where p.email =:email", Parent.class).setParameter("email", user.getEmail()).getSingleResult();
		System.out.println("CHILD OF PARENT : " + parent.getChild_player().getFirstname());
		return parent.getChild_player();
	}

	
	
	public Tournament getTournamentOfPlayer(Player playerOfParent) {
		Match game = getCurrentGameOfPlayer(playerOfParent);
		System.out.println("Tournamement of the player : "+game.getTournament());
		return game.getTournament();
	}

	private Match getCurrentGameOfPlayer(Player playerOfParent) {
		Match game = entityManager.createQuery("from Game g WHERE g.gameStatus=:status AND g.player1 =:player1 OR g.player2 =:player2", Match.class)
				.setParameter("status", GameStatus.NOW_PLAYING).setParameter("player1", playerOfParent).setParameter("player2", playerOfParent).getSingleResult();
		return game;
	}

	public MatchScore getGameSetOfPlayer(Player playerOfParent) {
		Match game = getCurrentGameOfPlayer(playerOfParent);
		List<MatchScore> gameSet = entityManager.createQuery("from GameSet g WHERE g.game =:game", MatchScore.class)
				.setParameter("game", game).getResultList();
		return gameSet.get(gameSet.size()-1);
	}


}
