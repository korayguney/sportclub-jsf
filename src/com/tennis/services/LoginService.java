package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Admin;
import com.tennis.models.Login;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.User;
import com.tennis.utils.HashAlgorithm;
import com.tennis.utils.HashingUtils;

@Stateless
public class LoginService {

	@PersistenceContext
	EntityManager entityManager;

	public User checkUserOnDatabase(Login login) {

		try {
			TypedQuery<Login> q = entityManager
					.createQuery("SELECT l FROM Login l WHERE l.email =:email AND l.password =:password", Login.class);
			q.setParameter("email", login.getEmail());
			q.setParameter("password", HashingUtils.hashPassword(login.getPassword(), HashAlgorithm.SHA256).toString());
			Login loginResult = q.getSingleResult();

			return ((loginResult != null) ? getUserFromDatabase(loginResult) : null);

		} catch (Exception e) {
			return null;
		}

	}


	private User getUserFromDatabase(Login login) {

		System.out.println("LOGIN : " + login.toString());

		switch (login.getRole()) {
		case ADMIN:
			Admin admin = entityManager.createQuery("SELECT a FROM Admin a WHERE a.email =:email", Admin.class)
					.setParameter("email", login.getEmail()).getSingleResult();
			return (User) admin;

		case PARENT:
			Parent parent = entityManager.createQuery("SELECT p FROM Parent p WHERE p.email =:email", Parent.class)
					.setParameter("email", login.getEmail()).getSingleResult();
			return (User) parent;

		case PLAYER:
			Player player = entityManager.createQuery("SELECT p FROM Player p WHERE p.email =:email", Player.class)
					.setParameter("email", login.getEmail()).getSingleResult();
			return (User) player;
		}

		return null;
	}

}
