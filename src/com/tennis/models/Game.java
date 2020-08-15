package com.tennis.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String score1;
	private String score2;
	private LocalDate date;
	private LocalTime time;
	private String place;
	private int court;
	private GameStatus gameStatus;
	
//	@OneToMany (fetch = FetchType.EAGER)
//	private List<Player> playersOfGame = new ArrayList<Player>();
	@OneToOne
	private Player player1;
	@OneToOne
	private Player player2;
	
	@ManyToOne (cascade = CascadeType.MERGE)
	private Tournament tournament;
	
	public enum GameStatus {
		NOT_PLAYED_YET, NOW_PLAYING, FINISHED;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getScore1() {
		return score1;
	}
	public void setScore1(String score1) {
		this.score1 = score1;
	}
	
	public String getScore2() {
		return score2;
	}
	public void setScore2(String score2) {
		this.score2 = score2;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
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
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", score=" + score1+ "-" +score2 + ", date=" + date + ", time=" + time + ", place=" + place
				+ ", court=" + court + ", player1=" + player1 + ", player2=" + player2 + ", tournament=" + tournament
				+ "]";
	}
	
	


	
	
	
	
	
}
