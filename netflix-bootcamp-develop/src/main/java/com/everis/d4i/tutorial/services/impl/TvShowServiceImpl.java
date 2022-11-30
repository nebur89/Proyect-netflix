
package com.everis.d4i.tutorial.services.impl;
import java.util.List;
import java.util.stream.Collectors;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NoContentExeption;
import com.everis.d4i.tutorial.json.AwardRest;


import com.everis.d4i.tutorial.repositories.CategoryRepository;
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


/**
 * TV-SHOW SERVICE IMPLEMENT
 */

@Service
public class TvShowServiceImpl implements TvShowService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TvShowServiceImpl.class);
	@Autowired
	private TvShowRepository tvShowRepository;

	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();


	/**
	 * Return list TvShow by idCategory
	 * @param categoryId
	 * @return List<TvShowRest>
	 * @throws NetflixException
	 */
	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		categoryRepository.findById(categoryId)
				.orElseThrow( () -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CATEGORY));

		return tvShowRepository.findByCategoryList_CategoryId(categoryId)
				.stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class))
				.collect(Collectors.toList());

	}


	/**
	 * Return TvShow by idTvShow
	 * @param id
	 * @return TvShowRest
	 * @throws NetflixException
	 */
	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		return modelMapper.map(
				tvShowRepository.findById(id)
						.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW))
				, TvShowRest.class);


	}



	/**
	 * Rename  TvShow by idTvShow
	 * @param tvShowId
	 * @param tvShowName
	 * @return TvShowRest
	 * @throws NetflixException
	 */
	@Override
	public TvShowRest renameTvShow(Long tvShowId, String tvShowName) throws NetflixException {

	    TvShow tvShow= tvShowRepository.findById(tvShowId)
				.orElseThrow(() -> new NoContentExeption(ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW));
		tvShow.setName(tvShowName);

		TvShow tvShow1= tvShowRepository.save( tvShow);
		return  modelMapper.map(tvShow1, TvShowRest.class);

	}


	/**
	 * Delete  TvShow by TvShow
	 * @param tvShowId
	 * @throws NetflixException
	 */
	@Override
	public void deleteTvShow(Long tvShowId) throws NetflixException {

		TvShow tvShow=  tvShowRepository.findById(tvShowId)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW));

		tvShowRepository.delete(tvShow);

	}


	/**
	 * List  TvShow´s Awards  by idTvShow
	 * @param tvShowId
	 * @return List<AwardRest>
	 * @throws NetflixException
	 */
	@Override
	public List<AwardRest> listAllAwards(Long tvShowId) throws NetflixException {

		return tvShowRepository.findById(tvShowId)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TVSHOW))
				.getAwardsList()
				.stream()
				.map(award->modelMapper.map(award, AwardRest.class))
				.collect(Collectors.toList());

	}






}
