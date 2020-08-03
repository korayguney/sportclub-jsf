package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.models.User;

@Stateless
public class UserService {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public List<User> getAllUsers() {
		List<User> users = entityManager.createQuery("from User", User.class).getResultList();
		return users;
	}


	public void saveUser(User user) {
		entityManager.persist(user);
	}


	public void deleteUser(User user) {
		user = entityManager.find(User.class, user.getId());
		entityManager.remove(user);
	}

}
