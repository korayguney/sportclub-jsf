package com.tennis.mbeans;

import java.time.LocalDate;
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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Game;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
public class GameListBean {

	private List<Game> games;
	private Tournament tournament;
	private Game game;
	private List<Player> players;
	private Date gamedate;
	private String gamehour;

	private Player player1;
	private Player player2;
	
	private int player1id;
	private int player2id;

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

		tournament = new Tournament();
		this.players = new ArrayList<Player>();
		this.players = userService.getAllPlayers();
		game = new Game();
		try {
			
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			int tournamentId = Integer.valueOf(req.getParameter("tournamentId"));
			tournament = tournamentService.getTournament(tournamentId);
			sessionScopeBean.setTournament(tournament);
			games = gameService.getAllGames(tournament);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public String saveGame(int player1id, int player2id) {

		this.game.setDate(getGamedate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		this.game.setTime(LocalTime.parse(gamehour));
		
		this.player1id = player1id;
		this.player2id = player2id;
		
		this.player1 = gameService.findPlayer(this.player1id);
		this.player2 = gameService.findPlayer(this.player2id);
		
		this.game.setPlayer1(this.player1);
		this.game.setPlayer2(this.player2);
		
		this.game.setPlace(tournament.getTour_place());
		this.game.setTournament(sessionScopeBean.getTournament());
		
		gameService.saveGame(game);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is succesfully saved"));

		return "secure/addgame";

	}

	public String deleteGame(Game game) {
		gameService.deleteGame(game);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "secure/gamelist?faces-redirect=true";

	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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

	public String getGamehour() {
		return gamehour;
	}

	public void setGamehour(String gamehour) {
		this.gamehour = gamehour;
	}

	public SessionScopeBean getSessionScopeBean() {
		return sessionScopeBean;
	}

	public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
		this.sessionScopeBean = sessionScopeBean;
	}

	public int getPlayer1id() {
		return player1id;
	}

	public void setPlayer1id(int player1id) {
		this.player1id = player1id;
	}

	public int getPlayer2id() {
		return player2id;
	}

	public void setPlayer2id(int player2id) {
		this.player2id = player2id;
	}

	
	
}
