package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	//**ATTRIBUTES *************************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHAPTER_ID")
	private Long chapterId;

	@Column(name = "NUMBER")
	private short number;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DURATION")
	private short duration;

	//***RELATION *****************************

	//Season
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEASON_ID", nullable = false)
	private Season season;


	//Actor
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "CHAPTER_ACTOR",
			joinColumns= @JoinColumn(name = "CHAPTER_ID"),
			inverseJoinColumns= @JoinColumn(name = "ACTOR_ID"))
	private Set<Actor> actorList;



	//**BUILDER *******************************

	public Chapter() {}

	public Chapter(Long chapterId, short number, String name, short duration, Season season
			, Set<Actor> actorList
	) {
		this.chapterId = chapterId;
		this.number = number;
		this.name = name;
		this.duration = duration;
		this.season = season;
	   this.actorList = actorList;
	}

//***GET AND SET ****************************************


	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Set<Actor> getActorList() { return actorList; }

	public void setActorList(Set<Actor> actorList) { this.actorList = actorList; }


}

