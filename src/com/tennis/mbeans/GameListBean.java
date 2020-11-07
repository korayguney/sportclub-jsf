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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Match;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.models.Match.GameStatus;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
public class GameListBean {

	private Match game;
	private Tournament tournament;
	private List<Match> games;
	private String gamehour;
	private List<Player> players;
	private Date gamedate;
	private Player player1;
	private Player player2;
	private int player1id;
	private int player2id;


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
		tournament = new Tournament();
		players = userService.getAllPlayers();
		game = new Match();
		
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			int tournamentId = Integer.parseInt(req.getParameter("tournamentId"));
			tournament = tournamentService.getTournament(tournamentId);
			sessionScopeBean.setTournament(tournament);
			games = gameService.getAllGames(this.tournament);
		} catch (Exception e) {
			System.out.println("Inside catch init() of GameListBean");
		}

		

	}

	public String saveGame(int player1id, int player2id) {
		
		System.out.println("Player1 id: " + player1id + " , Player2 id: " + player2id);
		
		this.game.setDate(getGamedate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		this.game.setTime(LocalTime.parse(gamehour));
		
		this.player1id = player1id;
		this.player2id = player2id;
		
		this.player1 = userService.getPlayer(this.player1id);
		this.player2 = userService.getPlayer(this.player2id);
		
		System.out.println("Player1 : " + player1 + " , Player2: " + player2);

		
//		List<Player> selectedPlayer = new ArrayList<>();
//		selectedPlayer.add(player1);
//		selectedPlayer.add(player2);
		
		//this.game.setPlayersOfGame(selectedPlayer);
		 
		this.game.setPlayer1(player1); 
		this.game.setPlayer2(player2);
		this.game.setPlace(sessionScopeBean.getTournament().getTour_place());
		this.game.setTournament(sessionScopeBean.getTournament());
		this.game.setGameStatus(GameStatus.NOT_PLAYED_YET);
		gameService.saveGame(this.game);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is saved "));

		return "secure/addgame";

	}

	public String deleteGame(Match game) {
		gameService.deleteGame(game);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "#{request.contextPath}/../secure/gamelist?faces-redirect=true";
	}

	public Match getGame() {
		return game;
	}

	public void setGame(Match game) {
		this.game = game;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Match> getGames() {
		return games;
	}

	public void setGames(List<Match> games) {
		this.games = games;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public String getGamehour() {
		return gamehour;
	}

	public void setGamehour(String gamehour) {
		this.gamehour = gamehour;
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
