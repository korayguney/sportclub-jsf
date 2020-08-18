package com.tennis.mbeans;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Game;
import com.tennis.models.Game.GameStatus;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
@ViewScoped
public class ScoreGameBean {

	private Tournament tournament;
	private Game game;
	private List<Player> players;
	private Date gamedate;

	private Player player1;
	private Player player2;

	@EJB
	private GameService gameService;

	@EJB
	private TournamentService tournamentService;

	@EJB
	private UserService userService;

	@ManagedProperty(value = "#{sessionScopeBean}")
	SessionScopeBean sessionScopeBean;

	@PostConstruct
	public void init() {
		
		this.players = userService.getAllPlayers();
		game = new Game();

		try {

			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			int gameId = Integer.valueOf(req.getParameter("gameId"));
			game = gameService.getGame(gameId);
			sessionScopeBean.setGame(game);
			System.out.println(game);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void startGame(Game game) {

		this.game = game;
		this.game.setGameStatus(GameStatus.NOW_PLAYING);
		gameService.startGame(this.game);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is started"));

	}

	public void submitScore(int game_number, int set_number, int score1, int score2 ) {
		
		sessionScopeBean.getGame().setGame_number(game_number);
		sessionScopeBean.getGame().setSet_number(set_number);
		sessionScopeBean.getGame().setScore1(score1);
		sessionScopeBean.getGame().setScore1(score2);
		gameService.submitScore(sessionScopeBean.getGame());
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Score is saved"));

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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Date getGamedate() {
		return gamedate;
	}

	public void setGamedate(Date gamedate) {
		this.gamedate = gamedate;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SessionScopeBean getSessionScopeBean() {
		return sessionScopeBean;
	}

	public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
		this.sessionScopeBean = sessionScopeBean;
	}

}
