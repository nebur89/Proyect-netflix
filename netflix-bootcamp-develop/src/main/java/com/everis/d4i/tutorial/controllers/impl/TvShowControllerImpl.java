package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;


import com.everis.d4i.tutorial.json.AwardRest;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.everis.d4i.tutorial.controllers.TvShowController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

/**
 * TV-SHOW CONTROLLER IMPLEMENT
 */

@RestController
//@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
@RequestMapping(RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

	@Autowired
	private TvShowService tvShowService;


	/**
	 * Return list TvShow by categoryId
	 * @param categoryId
	 * @return NetflixResponse<List<TvShowRest>>
	 * @throws NetflixException
	 */
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_CATEGORY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(
			@ApiParam(value = RestConstants.PARAMETER_CATEGORY_ID, required = true)  @PathVariable Long categoryId)
			throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowsByCategory(categoryId));
	}


	/**
	 * Return TvShow by idTvShow
	 * @param id
	 * @return Return TvShow by idTvShow
	 * @throws NetflixException
	 */
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> getTvShowById(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)	@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowById(id));
	}


	/**
	 * Rename  tvShow by 0idTvShow
	 * @param tvShowId
	 * @param tvShowName
	 * @return NetflixResponse<TvShowRest>
	 * @throws NetflixException
	 */
	@Override
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RESOURCE_TV_SHOW_RENAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> renameTvShow(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)  @PathVariable Long tvShowId,
			@ApiParam( value = RestConstants.PARAMETER_TV_SHOW_NAME, required = true) @PathVariable  String tvShowName)
			throws NetflixException {


		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.renameTvShow(tvShowId, tvShowName));
	}


	/**
	 * Delete  tvShow by TvShow
	 * @param tvShowId
	 * @return NetflixResponse<Void>
	 * @throws NetflixException
	 */
	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping (value = RestConstants.RESOURCE_TV_SHOW_ID)
	public NetflixResponse<Void> deleteTvShow(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)
			@PathVariable Long tvShowId) throws NetflixException {


		tvShowService.deleteTvShow(tvShowId);

		return new NetflixResponse<>(CommonConstants.SUCCESS,String.valueOf(HttpStatus.NO_CONTENT) , CommonConstants.OK);


	}




	/**
	 * List  TvShow´s Awards  by idTvShow
	 * @param tvShowId
	 * @return NetflixResponse<List<AwardRest>>
	 * @throws NetflixException
	 */
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_TV_SHOW_AWARDS, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<AwardRest>> listAllAwards(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)
			@PathVariable Long tvShowId) throws NetflixException {


		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.listAllAwards(tvShowId));
	}


}
