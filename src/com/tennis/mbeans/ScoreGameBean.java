package com.tennis.mbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Game;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
public class ScoreGameBean {

	private Game game;
	private Tournament tournament;
	private List<Player> players;
	private Date gamedate;
	private Player player1;
	private Player player2;

	@EJB
	GameService gameService;

	@EJB
	TournamentService tournamentService;
	
	@EJB
	UserService userService;
	
	@ManagedProperty(value = "#{sessionScopeBean}")
	SessionScopeBean sessionScopeBean;

	@PostConstruct
	public void init() {
		
		players = userService.getAllPlayers();
		
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			int gameId = Integer.parseInt(req.getParameter("gameId"));
			game = gameService.getGame(gameId);
			sessionScopeBean.setGame(game);
			System.out.println(">>>> " + game);
		} catch (Exception e) {
			System.out.println("Inside catch init() of ScoreGameBean");
		}

		

	}



	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Date getGamedate() {
		return gamedate;
	}

	public void setGamedate(Date gamedate) {
		this.gamedate = gamedate;
	}

	public SessionScopeBean getSessionScopeBean() {
		return sessionScopeBean;
	}

	public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
		this.sessionScopeBean = sessionScopeBean;
	}
	
	
}
