package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorRest2;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;

/**
 * ACTOR CONTROLLER INTERFACE
 */

public interface ActorController {

        NetflixResponse<ActorRest> createNewActor(ActorRest actorRest) throws NetflixException;

       NetflixResponse<ActorRest> updateActor(ActorRest actorRest) throws NetflixException;

       NetflixResponse<Void> deleteActor(Long actorId)throws  NetflixException;

       NetflixResponse<List<ActorRest>> listAllActor() throws NetflixException;

       NetflixResponse<ActorRest> findByID(Long actorId)throws NetflixException;

       NetflixResponse<ActorRest2> findActorWithTvShowAndChapters(Long actorId)throws NetflixException;


}
