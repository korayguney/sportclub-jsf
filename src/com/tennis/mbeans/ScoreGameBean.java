package com.tennis.mbeans;

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
import com.tennis.models.MatchSet;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
@ViewScoped
public class ScoreGameBean {

	private Game game;
	private Tournament tournament;
	private List<Player> players;
	private Date gamedate;
	private Player player1;
	private Player player2;
	private MatchSet gameSet;

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
		game = new Game();
		gameSet = new MatchSet();

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

	public void startGame(Game game) {
		this.game = game;
		this.game.setGameStatus(GameStatus.NOW_PLAYING);
		this.gameSet.setSet_no(1);
		gameService.startGame(this.game);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is started "));
	}

	public void submitScore(int score1, int score2) {
		if (this.game.getGameStatus().equals(GameStatus.NOW_PLAYING)) {

			this.gameSet.setScore1(score1);
			this.gameSet.setScore2(score2);
			this.gameSet.setGame(sessionScopeBean.getGame());
			gameService.submitScore(this.gameSet);
			
			if(score1 == 6 || score2 == 6) {
				int set = this.gameSet.getSet_no();
				
				if(this.gameSet.getSet_no() != 6) {
					this.gameSet.setSet_no(++set);
				} else {
					// TO-DO finish game method at service
				}
				
				Player winner = new Player();
				
				List<Integer> scores = gameService.getLastSetScores(sessionScopeBean.getGame());
				
				if(score1 > score2) {
					winner = sessionScopeBean.getGame().getPlayer1();
					int setscore1 = scores.get(0);
					sessionScopeBean.getGame().setSet_score1(++setscore1);
					gameService.submitSetScore(sessionScopeBean.getGame());
				} else {
					winner = sessionScopeBean.getGame().getPlayer2();
					int setscore2 = scores.get(1);
					sessionScopeBean.getGame().setSet_score2(++setscore2);
					gameService.submitSetScore(sessionScopeBean.getGame());
				}
				
				this.gameSet.setScore1(0);
				this.gameSet.setScore2(0);
				
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Set is submitted with scores", "Winner of the set : " + 
								winner.getFirstname() +  " " + winner.getLastname()));
				
			}
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Score is saved "));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "game has not started! ", " press start game button"));
			
			
		}

	}

	public void submitGame(int game, int period, int score1, int score2) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is saved "));

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

	public MatchSet getGameSet() {
		return gameSet;
	}

	public void setGameSet(MatchSet gameSet) {
		this.gameSet = gameSet;
	}

}
