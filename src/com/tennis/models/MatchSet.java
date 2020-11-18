package com.tennis.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MatchSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int score1;
	private int score2;
	private int set_no;
	
	@OneToOne
	private Game game;
	
	
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
	public int getSet_no() {
		return set_no;
	}
	public void setSet_no(int set_no) {
		this.set_no = set_no;
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	@Override
	public String toString() {
		return "GameSet [id=" + id + ", score1=" + score1 + ", score2=" + score2 + ", set_no=" + set_no + ", game="
				+ game + "]";
	}
	
	
	
	
	
	
}
