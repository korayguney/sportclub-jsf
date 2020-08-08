package com.tennis.mbeans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Game;
import com.tennis.models.Role;
import com.tennis.models.Tournament;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;

@ManagedBean
public class GameListBean {

	private Game game;
	private Tournament tournament;
	private List<Game> games;

	@EJB
	GameService gameService;

	@EJB
	TournamentService tournamentService;

	@PostConstruct
	public void init() {
		tournament = new Tournament();

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		int tournamentId = Integer.parseInt(req.getParameter("tournamentId"));
		tournament = tournamentService.getTournament(tournamentId);
		
		System.out.println("Tournament is taken init : " + tournament.getTour_name());
		
		games = gameService.getAllGames(this.tournament);

	}

	public String saveGame() {
		this.game.setTournament(this.tournament);
		gameService.saveGame(this.game);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Game is saved "));

		return "secure/addgame";

	}

	

	public String deleteGame(Game game) {
		gameService.deleteGame(game);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "#{request.contextPath}/../secure/gamelist?faces-redirect=true";
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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
