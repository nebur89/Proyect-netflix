package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRest implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;


	//Attributes
	private Long id;
	private String name;

	//Builder

	public CategoryRest(){}

	public CategoryRest(String name) {
		this.name = name;
	}

	public CategoryRest(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//get and set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
