package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorRest2;

import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

/*ACTOR CONTROLLER IMPLEMENT*/

@Service
public class ActorServiceImpl implements ActorService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

    @Autowired
    ActorRepository actorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    /*Create new Actor*/
    @Override
    public void creatorNewActor(ActorRest actorRest) throws NetflixException {

            Actor actor = modelMapper.map(actorRest, Actor.class);
            actorRepository.save(actor);

    }


    /*Update Actor */
    @Override
    public void updateActor(ActorRest actorRest) throws NetflixException {

      Actor actor=   actorRepository.findById(actorRest.getActorId())
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

        actor.setName(actorRest.getName());
        actor.setAge(actorRest.getAge());
        actor.setNationality(actorRest.getNationality());
        actor.setShortDescription(actorRest.getShortDescription());

        actorRepository.save(actor);

    }




    /* Delete Actor by actorId*/
    @Override
    public void deleteActor(Long actorId) throws NetflixException {

        Actor actor=   actorRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

        actorRepository.delete(actor);

    }


    /*Return list all Actor*/
    @Override
    public List<ActorRest> listAllActor() throws NetflixException {

            return actorRepository.findAll()
                    .stream()
                    .map(actor -> modelMapper.map(actor, ActorRest.class))
                    .collect(Collectors.toList());

    }


    /*Return Actor by actorId*/
    @Override
    public ActorRest findById(Long actorId) throws NetflixException {

        return   actorRepository.findById(actorId).map(actor -> modelMapper.map(actor,ActorRest.class))
                    .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));



    }


    @Override
    public ActorRest2 findAllTvShowAndChapter(Long actorId) throws NetflixException {


        /*Check if exist*/
       Actor actor=  actorRepository.findById(actorId)
               .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));


       /*Create list TvShoeRest*/
       Set<TvShowRest> chaTvShowSet= actor.getChapterList()
                .stream()
                .map(chapter -> chapter.getSeason().getTvShow())
                .distinct()
                .map(tvShow ->modelMapper.map(tvShow,TvShowRest.class))
                .collect(Collectors.toSet());

       /*Mapped actor to ActorRest*/
        ActorRest2 actorRest2= modelMapper.map(actor,ActorRest2.class);

        /*Add list tvShowsRest to actorRest2 */
        actorRest2.setTvShowRestsList(chaTvShowSet);


        return  actorRest2;

    }


}