package com.tennis.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Login;
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
	}

}
