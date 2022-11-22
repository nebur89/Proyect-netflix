package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;

import com.everis.d4i.tutorial.utils.constants.ServiceRestConstans;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.everis.d4i.tutorial.controllers.ChapterController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;


/* CHAPTER CONTROLLER IMPLEMENT*/
@RestController
//@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CHAPTER)
@RequestMapping(RestConstants.RESOURCE_CHAPTER)
public class ChapterControllerImpl implements ChapterController {


	private static  final Logger LOGGER= LoggerFactory.getLogger(ChapterControllerImpl.class);

	@Autowired
	private ChapterService chapterService;



	/* Return List chapter by tvShow and seasonNumber*/
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value =RestConstants.RESOURCE_CHAPTER_LIST , produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)	@PathVariable Long tvShowId,
			@ApiParam(value = RestConstants.PARAMETER_SEASON_NUMBER, required = true) @PathVariable short seasonNumber)
			throws NetflixException {


		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber));
	}


	/* Return chapter by seasonNumber and chapterNumber*/
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value =RestConstants.RESOURCE_CHAPTER_NUMBER , produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)	@PathVariable Long tvShowId,
			@ApiParam(value = RestConstants.PARAMETER_SEASON_NUMBER, required = true) @PathVariable short seasonNumber,
			@ApiParam(value = RestConstants.PARAMETER_CHAPTER_NUMBER, required = true) @PathVariable short chapterNumber)
			throws NetflixException {

		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, chapterNumber));
	}



	/* Rename chapter by  tvShowId, seasonNumber and chapterNumber and chapterNumber*/
	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value=RestConstants.RESOURCE_RENAME_CHAPTER, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<Void> renameChapter(
			@ApiParam(value = RestConstants.PARAMETER_TV_SHOW_ID, required = true)	@PathVariable Long tvShowId,
			@ApiParam(value = RestConstants.PARAMETER_SEASON_NUMBER, required = true) @PathVariable short seasonNumber,
			@ApiParam(value = RestConstants.PARAMETER_CHAPTER_NUMBER, required = true) @PathVariable short chapterNumber,
			@ApiParam(value = RestConstants.PARAMETER_CHAPTER_NAME, required = true) @PathVariable String newChapterName)
			throws NetflixException {

		chapterService.renameChapter(tvShowId, seasonNumber, chapterNumber, newChapterName);

		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
	}


}
