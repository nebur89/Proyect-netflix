package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.sun.xml.internal.bind.v2.TODO;
import org.aspectj.weaver.internal.tools.PointcutDesignatorHandlerBasedPointcut;

/* CATEGORY CONTROLLER INTERFACE*/
public interface CategoryController {

    /* Return List category */
	NetflixResponse<List<CategoryRest>> getCategories() throws NetflixException;



	/* Create new category */
	NetflixResponse<CategoryRest> createCategory(CategoryRest categoryRest) throws NetflixException;



}
