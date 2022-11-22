package com.everis.d4i.tutorial.services.impl;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.utils.constants.ServiceRestConstans;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

import javax.persistence.EntityNotFoundException;


/* CHAPTER SERVICE IMPLEMENT*/
@Service
public class ChapterServiceImpl implements ChapterService {


	private  static final Logger LOGGER= LoggerFactory.getLogger(ChapterServiceImpl.class);

	@Autowired
	private ChapterRepository chapterRepository;

	private ModelMapper modelMapper = new ModelMapper();


	/* Return List chapter by tvShowId and seasonNumber*/
	@Override
	public List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException {

		return chapterRepository.findBySeason_TvShow_TvShowIdAndSeason_Number(tvShowId, seasonNumber)
				.stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRest.class)).collect(Collectors.toList());

	}



	/* Return chapter by tvShowId , seasonNumber and chapterNumber*/
	@Override
	public ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException {
		Chapter chapter = chapterRepository
				.findBySeason_TvShow_TvShowIdAndSeason_NumberAndNumber(tvShowId, seasonNumber, chapterNumber)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CHAPTER));
		return modelMapper.map(chapter, ChapterRest.class);
	}



	/* Rename chapter by tvShowId , seasonNumber and chapterNumber*/
	@Override
	public void renameChapter(Long tvShowId, short seasonNumber, short chapterNumber , String newNameChapter) throws NetflixException {

			Chapter chapter = chapterRepository.findBySeason_TvShow_TvShowIdAndSeason_NumberAndNumber(tvShowId,seasonNumber,chapterNumber)
					.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CHAPTER));

			chapter.setName(newNameChapter);
			chapterRepository.save(chapter);

	}
}
