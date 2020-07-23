package com.tennis.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Player extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int age;
	private Gender gender;
	
	@ManyToMany
	private List<Tournament> tournaments = new ArrayList<Tournament>();
	
	public enum Gender {
		MALE,
		FEMALE;
	}

		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}
	
	
	
	
}
