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

@RestController
//@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CHAPTER)
@RequestMapping(RestConstants.RESOURCE_CHAPTER)

public class ChapterControllerImpl implements ChapterController {


	private static  final Logger LOGGER= LoggerFactory.getLogger(ChapterControllerImpl.class);

	@Autowired
	private ChapterService chapterService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber, @PathVariable short number) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, number));
	}


	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value=RestConstants.RESOURCE_RENAME_CHAPTER, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<String> renameChapter(
			@ApiParam(value = RestConstants.DESCRIPTION_CHAPTER ,required = true)
			@PathVariable Long tvShowId,
			@PathVariable short seasonNumber,
			@PathVariable short chapterNumber,
			@PathVariable String newNameChapter )
			throws NetflixException {

		System.out.println("ok");
		LOGGER.info("ok");

		String message= chapterService.renameChapter(tvShowId,seasonNumber, chapterNumber, newNameChapter);
		String code= (message.equals(ServiceRestConstans.MESSAGE_UPDATE_CHAPTER))? String.valueOf(HttpStatus.OK):
				String.valueOf(HttpStatus.NOT_FOUND);

		return new  NetflixResponse<>(CommonConstants.SUCCESS, code, message);
	}


}
