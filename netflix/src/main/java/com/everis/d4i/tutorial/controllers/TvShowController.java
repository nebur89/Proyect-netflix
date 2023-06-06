
package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;


/**
 * TV-SHOW CONTROLLER INTERFACE
 */
public interface TvShowController {

	 NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	 NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	 NetflixResponse<TvShowRest> renameTvShow(Long tvShowId, String tvShowName) throws NetflixException;

	 NetflixResponse<Void> deleteTvShow(Long tvShowId) throws NetflixException;

	 NetflixResponse<List<AwardRest>>  listAllAwards(Long tvShowId)throws NetflixException;

}
