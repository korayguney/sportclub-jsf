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

import com.tennis.models.Tournament;
import com.tennis.services.TournamentService;

@ManagedBean
public class TournamentListBean {

	private List<Tournament> tournaments;
	private Tournament tournament;
	private Date tour_start_date;
	private Date tour_finish_date;
	
	@EJB
	private TournamentService tournamentService;
	
	@PostConstruct
	public void init() {
		tournaments = tournamentService.getAllTournaments();
		tournament = new Tournament();
	}
	
	public String saveTournament() {
		convertDateToLocaldate();
		
		tournamentService.saveTournament(this.tournament);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tournament is succesfully saved"));

		return "secure/addtournament";
		
		
	}
	
	public void convertDateToLocaldate() {
		LocalDate start_date = getTour_start_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate finish_date = getTour_finish_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.tournament.setTour_start_date(start_date);
		this.tournament.setTour_finish_date(finish_date);
	}
	
	
	public String deleteTournament(Tournament tournament) {
		tournamentService.deleteTournament(tournament);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "secure/userlist?faces-redirect=true";

	}

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
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

	public Date getTour_start_date() {
		return tour_start_date;
	}

	public void setTour_start_date(Date tour_start_date) {
		this.tour_start_date = tour_start_date;
	}

	public Date getTour_finish_date() {
		return tour_finish_date;
	}

	public void setTour_finish_date(Date tour_finish_date) {
		this.tour_finish_date = tour_finish_date;
	}

	
	
	
	
	
}
