package com.tennis.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tennis.models.Game;

@Entity
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String tour_name;
	private String tour_place;
	private LocalDate tour_start_date;
	private LocalDate tour_finish_date;
	
	
	@OneToMany(mappedBy = "tournament" )
	private List<Game> games = new ArrayList<Game>();
	
	@ManyToMany (mappedBy = "tournaments")
	private List<Player> players = new ArrayList<Player>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Game> getGame() {
		return games;
	}
	public void setGame(List<Game> game) {
		this.games = game;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public String getTour_place() {
		return tour_place;
	}
	public void setTour_place(String tour_place) {
		this.tour_place = tour_place;
	}
	public LocalDate getTour_start_date() {
		return tour_start_date;
	}
	public void setTour_start_date(LocalDate tour_start_date) {
		this.tour_start_date = tour_start_date;
	}
	public LocalDate getTour_finish_date() {
		return tour_finish_date;
	}
	public void setTour_finish_date(LocalDate tour_finish_date) {
		this.tour_finish_date = tour_finish_date;
	}
	
	
	
	
	
	
	
	
}
