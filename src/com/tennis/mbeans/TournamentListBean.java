package com.tennis.mbeans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

	private Tournament tournament;
	private Date tournament_start_date;
	private Date tournament_finish_date;
	private List<Tournament> tournaments;

	@EJB
	TournamentService tournamentService;

	@PostConstruct
	public void init() {
		tournaments = tournamentService.getAllTournaments();
		tournament = new Tournament();
	}

	public String saveTournament() {
		System.out.println(" tarih : " + getTournament_start_date() );
		convertStringDateToLocalDate();
		System.out.println("Tournament : " + this.tournament);
		boolean result = tournamentService.saveTournament(this.tournament);
		
		if(result) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tournament is saved "));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Tournament is not saved "));
		}
		
		return "secure/addtournament";

	}

	private void convertStringDateToLocalDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

		LocalDate start_date = getTournament_start_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate finish_date = getTournament_finish_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		this.tournament.setTour_start_date(start_date);
		this.tournament.setTour_finish_date(finish_date);
	}

	public String deleteTournament(Tournament tournament) {
		tournamentService.deleteTournament(tournament);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "secure/tournamentlist?faces-redirect=true";
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

	public Date getTournament_start_date() {
		return tournament_start_date;
	}

	public void setTournament_start_date(Date tournament_start_date) {
		this.tournament_start_date = tournament_start_date;
	}

	public Date getTournament_finish_date() {
		return tournament_finish_date;
	}

	public void setTournament_finish_date(Date tournament_finish_date) {
		this.tournament_finish_date = tournament_finish_date;
	}

	

	

	
}
