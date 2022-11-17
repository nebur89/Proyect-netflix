package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
public class Category implements Serializable {

	private static final long serialVersionUcategoryId = 180802329613616000L;


	//***ATTRIBUTES ************************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@Column(name = "NAME", unique = true)
	private String name;



	//***RELATION ********************************

	//Category

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryList")
	private Set<TvShow> tvShowsList;




	//**BUILDER ********************************
	public Category() {}

	public Category(String name){
		this.name=name;
	}

	public Category(Long categoryId, String name, Set<TvShow> tvShowsList) {
		this.categoryId = categoryId;
		this.name = name;
	 this.tvShowsList = tvShowsList;
	}




	//*** GET AND SET ***********************************************

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TvShow> getTvShowsList() {
		return tvShowsList;
	}

	public void setTvShowsList(Set<TvShow> tvShowsList) {
		this.tvShowsList = tvShowsList;
	}


}
