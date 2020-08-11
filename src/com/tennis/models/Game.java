package com.tennis.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int score;
	private LocalDate date;
	private LocalTime time;
	private String place;
	private int court;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> playersOfTheGame = new ArrayList<Player>();
	
	@ManyToOne
	private Tournament tournament;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getCourt() {
		return court;
	}
	public void setCourt(int court) {
		this.court = court;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public List<Player> getPlayersOfTheGame() {
		return playersOfTheGame;
	}
	public void setPlayersOfTheGame(List<Player> playersOfTheGame) {
		this.playersOfTheGame = playersOfTheGame;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDate() {
		return date;
	}
	
	
	
	
	
	
}
