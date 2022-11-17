
package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.utils.constants.ServiceRestConstans;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;






@Service
public class TvShowServiceImpl implements TvShowService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TvShowServiceImpl.class);
	@Autowired
	private TvShowRepository tvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();


	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		return tvShowRepository.findByCategoryList_CategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}


	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public String renameTvShow(TvShowRest tvShowRest) throws NetflixException {

		try {

			TvShow tvShow = new TvShow();
			Optional<TvShow> tvShow1= tvShowRepository.findById(tvShowRest.getId());

			if(tvShow1.isPresent()){
				tvShow= tvShow1.get();
				tvShow.setName(tvShowRest.getName());
				tvShowRepository.save(tvShow);

				return ServiceRestConstans.MESSAGE_UPDATE_TV_SHOW;
			}
			else {
				return ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW;
			}

		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public String deleteTvShow(TvShowRest tvShowRest) throws NetflixException {

		try {

			Optional<TvShow> tvShow= tvShowRepository.findById(tvShowRest.getId());

			if(tvShow.isPresent()){

				tvShowRepository.delete(tvShow.get());
				return ServiceRestConstans.MESSAGE_DELETE_TV_SHOW;

			} else {
				return ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW;
			}


		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}


	}
}
