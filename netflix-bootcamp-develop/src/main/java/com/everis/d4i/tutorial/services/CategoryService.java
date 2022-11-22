package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;

public interface CategoryService {

	/* Return List category */
	List<CategoryRest> getCategories() throws NetflixException;


	/* Create new category */
	CategoryRest createCategories(CategoryRest categoryRest) throws NetflixException;
}
