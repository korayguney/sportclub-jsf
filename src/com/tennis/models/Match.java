package com.tennis.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tennisgame")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private LocalTime time;
	private String place;
	private int court;
	private GameStatus matchStatus;
	private int set_score1;
	private int set_score2;


	@OneToOne
	private Player player1;
	@OneToOne
	private Player player2;

	@ManyToOne(cascade = CascadeType.MERGE)
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
		return matchStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.matchStatus = gameStatus;
	}

	public int getSet_score1() {
		return set_score1;
	}

	public void setSet_score1(int set_score1) {
		this.set_score1 = set_score1;
	}

	public int getSet_score2() {
		return set_score2;
	}

	public void setSet_score2(int set_score2) {
		this.set_score2 = set_score2;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", date=" + date + ", time=" + time + ", place=" + place + ", court=" + court
				+ ", gameStatus=" + matchStatus + ", set_score1=" + set_score1 + ", set_score2=" + set_score2
				+ ", player1=" + player1 + ", player2=" + player2 + ", tournament=" + tournament + "]";
	}

	

}
