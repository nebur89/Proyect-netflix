package com.everis.d4i.tutorial.services;


import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorRest2;


import java.util.List;

/**
 * ACTOR SERVICE  INTERFACE
 */

public interface ActorService {

    ActorRest creatorNewActor(ActorRest actorRest) throws NetflixException;

    ActorRest updateActor(ActorRest actorRest) throws  NetflixException;

    void deleteActor(Long actorId)throws  NetflixException;


    List<ActorRest> listAllActor()throws NetflixException;

    ActorRest  findById(Long actorId) throws NetflixException;

     ActorRest2 findAllTvShowAndChapter(Long actorId) throws NetflixException;


}
