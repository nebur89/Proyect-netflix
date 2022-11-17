package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;


import com.everis.d4i.tutorial.utils.constants.ServiceRestConstans;
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

import javax.validation.Valid;

@RestController
//@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
@RequestMapping(RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

	@Autowired
	private TvShowService tvShowService;


	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
			throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowsByCategory(categoryId));
	}



	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowById(id));
	}


	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<String> renameTvShow(
			@ApiParam(value = RestConstants.PARAMETER_CATEGORY, required = true) @RequestBody @Valid final	TvShowRest tvShowRest)
			throws NetflixException {

		String message= tvShowService.renameTvShow(tvShowRest);

		String code=(message.equals(ServiceRestConstans.MESSAGE_UPDATE_TV_SHOW))? String.valueOf(HttpStatus.OK):
				String.valueOf(HttpStatus.NOT_FOUND);

		return new NetflixResponse<>(CommonConstants.SUCCESS,code , message);
	}


	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping (produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<String> deleteTvShow(
			@ApiParam(value = RestConstants.PARAMETER_CATEGORY, required = true) @RequestBody @Valid final	TvShowRest tvShowRest)
			throws NetflixException {

		String message= tvShowService.deleteTvShow(tvShowRest);

		String code=(message.equals(ServiceRestConstans.MESSAGE_DELETE_TV_SHOW))? String.valueOf(HttpStatus.NO_CONTENT):
				String.valueOf(HttpStatus.NOT_FOUND);

		return new NetflixResponse<>(CommonConstants.SUCCESS, code, message);
	}




}
