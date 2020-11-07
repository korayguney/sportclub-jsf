package com.tennis.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GameSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int score1;
	private int score2;
	private int set_number;
	
	@OneToOne
	private Game game;

	public GameSet() {
		// TODO Auto-generated constructor stub
	}
	
	public GameSet(int score1, int score2, int set_number, Game game) {
		super();
		this.score1 = score1;
		this.score2 = score2;
		this.set_number = set_number;
		this.game = game;
	}

	
	public int getSet_number() {
		return set_number;
	}

	public void setSet_number(int set_number) {
		this.set_number = set_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "GameSet [id=" + id + ", score1=" + score1 + ", score2=" + score2 + ", set_number=" + set_number
				+ ", game=" + game + "]";
	}

	

	
	
	
}
