package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TV_SHOWS")
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	//***ATTRIBUTES ****************************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TV_SHOW_ID")
	private Long tvShowId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	private String longDescription;

	@Column(name = "YEAR")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	private byte recommendedAge;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;


	//***RELATION************

	//Category

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "CATEGORY_TVSHOW",
			joinColumns= @JoinColumn(name = "TV_SHOW_ID"),
			inverseJoinColumns= @JoinColumn(name = "ID_CATEGRY"))
	private Set<Category> categoryList;



	//Seasons
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	private List<Season> seasonsList;


	//Award
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "TVSHOW_AWARD",
			joinColumns= @JoinColumn(name = "TV_SHOW_ID"),
			inverseJoinColumns= @JoinColumn(name = "AWARD_ID"))
	private Set<Award> awardsList;


	//***BUILDER*****************************

	public TvShow() {}

	public TvShow(Long tvShowId, String name){
		this.tvShowId= tvShowId;
		this.name= name;
	}

	public TvShow(Long tvShowId, String name, String shortDescription, String longDescription,
				  Year year, byte recommendedAge, String advertising,  Set<Category> categoryList,
				  List<Season> seasonsList , Set<Award> awardsList
	) {
		this.tvShowId = tvShowId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.year = year;
		this.recommendedAge = recommendedAge;
		this.advertising = advertising;
		this.categoryList = categoryList;
		this.seasonsList = seasonsList;
		this.awardsList = awardsList;
	}

	//GET AND SET


	public Long getTvShowId() {
		return tvShowId;
	}

	public void setTvShowId(Long tvShowId) {
		this.tvShowId = tvShowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Season> getSeasonsList() {
		return seasonsList;
	}

	public void setSeasonsList(List<Season> seasonsList) {
		this.seasonsList = seasonsList;
	}

	public Set<Award> getAwardsList() {
		return awardsList;
	}

	public void setAwardsList(Set<Award> awardsList) {
		this.awardsList = awardsList;
	}


}
