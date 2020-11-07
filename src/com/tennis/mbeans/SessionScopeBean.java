package com.tennis.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tennis.models.Game;
import com.tennis.models.Parent;
import com.tennis.models.Tournament;
import com.tennis.models.User;

@ManagedBean
@SessionScoped
public class SessionScopeBean {
	
	private User user;
	private Tournament tournament;
	private Game game;
	private Parent parent;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	
	
	
}
