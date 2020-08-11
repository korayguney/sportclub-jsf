package com.tennis.mbeans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Game;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;

@ManagedBean
public class GameListBean {

	private List<Game> games;
	private Tournament tournament;
	private Game game;

	@EJB
	private GameService gameService;

	@EJB
	private TournamentService tournamentService;

	@PostConstruct
	public void init() {

		tournament = new Tournament();

		try {
			
		
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			int tournamentId = Integer.valueOf(req.getParameter("tournamentId"));
			tournament = tournamentService.getTournament(tournamentId);
	
			games = gameService.getAllGames(tournament);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public String saveGame() {

		gameService.saveGame(this.game);

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

}
