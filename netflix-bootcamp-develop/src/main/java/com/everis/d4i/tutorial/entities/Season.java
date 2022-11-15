package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SEASONS")
public class Season implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	//**ATTRIBUTES *************************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEASON_ID")
	private Long seasonId;

	@Column(name = "NUMBER")
	private short number;

	@Column(name = "NAME")
	private String name;

	//***RELATION *****************************

	//TvShow
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOW_ID", nullable = false)
	private TvShow tvShow;

	//Chapter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "season")
	private List<Chapter> chapterList;


	//**BUILDER *******************************

	public Season() {}

	public Season(Long seasonId, short number, String name , TvShow tvShow , List<Chapter> chapterList) {
		this.seasonId = seasonId;
		this.number = number;
		this.name = name;
		this.tvShow = tvShow;
		this.chapterList = chapterList;
	}

//***GET AND SET ****************************************


	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
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

	public TvShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShow tvShow) {
		this.tvShow = tvShow;
	}
	public List<Chapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}
}
