package com.everis.d4i.tutorial.controllers.impl;


import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorRest2;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
;
import com.everis.d4i.tutorial.utils.constants.ServiceRestConstans;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*ACTOR CONTROLLER IMPLEMENT*/


@RestController
@RequestMapping(RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {


    @Autowired
    ActorService actorService;

    @Autowired
    ActorRepository actorRepository;


    /*Create new Actor*/
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<Void> createNewActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true)
            @RequestBody ActorRest actorRest) throws NetflixException {

            actorService.creatorNewActor(actorRest);

            return new NetflixResponse<>( CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.OK);
    }


    /*Update Actor */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<Void> updateActor(
            @ApiParam(value= RestConstants.PARAMETER_ACTOR, required = true)
            @RequestBody ActorRest actorRest) throws NetflixException {

         actorService.updateActor(actorRest);

        return new NetflixResponse<>( CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
    }



/* Delete Actor by actorId*/
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ACTOR_ID)
    public NetflixResponse<Void> deleteActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
           @PathVariable Long actorId) throws NetflixException {

         actorService.deleteActor(actorId);

        return new NetflixResponse<>( CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);

    }

/*Return list all Actor*/
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ACTOR_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<ActorRest>> listAllActor()
            throws NetflixException {

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                actorService.listAllActor());

    }


    /*Return Actor by actorId*/
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping( value = RestConstants.RESOURCE_ACTOR_ID,   produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> findByID(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
                    @PathVariable Long actorId) throws NetflixException {

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                actorService.findById(actorId));

    }

    /*Return  list tvShow and list chapter from Actor  by actorId*/
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping( value = RestConstants.RESOURCE_ACTOR_FILE,   produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest2> findActorWithTvShowAndChapters(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
            @RequestParam Long actorId) throws NetflixException {


        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                actorService.findAllTvShowAndChapter(actorId));

    }



}
