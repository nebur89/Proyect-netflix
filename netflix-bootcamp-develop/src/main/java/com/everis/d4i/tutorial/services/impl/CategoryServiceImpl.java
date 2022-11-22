package com.everis.d4i.tutorial.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import com.everis.d4i.tutorial.exceptions.NotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.services.CategoryService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();


	/* Return List category */

	public List<CategoryRest> getCategories() throws NetflixException {

		try {
		return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryRest.class))
				.collect(Collectors.toList());

		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}

	}



	/* Create new category */
	public CategoryRest createCategories(final CategoryRest categoryRest) throws NetflixException {


		/*Check if exist */

		try {
			if(categoryRepository.findByName(categoryRest.getName()).isPresent()){
				throw  new NotFoundException(ExceptionConstants.MESSAGE_ALREADY_EXIST_CATEGORY);
			}
		}catch (final Exception e){

			LOGGER.error(ExceptionConstants.MESSAGE_ALREADY_EXIST_CATEGORY, e);
			throw new InternalServerErrorException(ExceptionConstants.MESSAGE_ALREADY_EXIST_CATEGORY);

		}


		try {

		Category category = new Category(categoryRest.getName());
		categoryRepository.save(category);
		return modelMapper.map(category, CategoryRest.class);

		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}


	}

}
