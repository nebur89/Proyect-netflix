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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ACTOR CONTROLLER IMPLEMENT
 */


@RestController
@RequestMapping(RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {


    @Autowired
    ActorService actorService;

    @Autowired
    ActorRepository actorRepository;


    /**
     * Create new Actor
     *
     * @param actorRest
     * @return NetflixResponse<ActorRest>
     * @throws NetflixException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> createNewActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true)
            @RequestBody ActorRest actorRest) throws NetflixException {


        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.OK,
                actorService.creatorNewActor(actorRest));
    }


    /**
     * Update Actor
     *
     * @param actorRest
     * @return NetflixResponse<ActorRest>
     * @throws NetflixException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> updateActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true)
            @RequestBody ActorRest actorRest) throws NetflixException {


        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.updateActor(actorRest));
    }


    /**
     * Delete Actor by actorId
     *
     * @param actorId
     * @return NetflixResponse<Void>
     * @throws NetflixException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ACTOR_ID)
    public NetflixResponse<Void> deleteActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
            @PathVariable Long actorId) throws NetflixException {

        actorService.deleteActor(actorId);

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);

    }

    /**
     * Return list all Actor
     *
     * @return NetflixResponse<List < ActorRest>>
     * @throws NetflixException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ACTOR_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<ActorRest>> listAllActor()
            throws NetflixException {

        System.out.println("entrada en controlador");

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.listAllActor());

    }


    /**
     * Return Actor by actorId
     *
     * @param actorId
     * @return NetflixResponse<ActorRest>
     * @throws NetflixException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ACTOR_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> findByID(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
            @PathVariable Long actorId) throws NetflixException {

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.findById(actorId));

    }

    /**
     * Return  list tvShow and list chapter from Actor  by actorId
     *
     * @param actorId
     * @return NetflixResponse<ActorRest2>
     * @throws NetflixException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ACTOR_FILE, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest2> findActorWithTvShowAndChapters(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR_ID, required = true)
            @RequestParam Long actorId) throws NetflixException {


        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.findAllTvShowAndChapter(actorId));

    }


}
