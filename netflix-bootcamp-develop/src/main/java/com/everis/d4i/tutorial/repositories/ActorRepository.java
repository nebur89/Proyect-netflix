
package com.everis.d4i.tutorial.repositories;


import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    boolean existsActorByActorId(Long actorId);
    Set<Chapter> findByChapterList_ActorList_ActorId(Long actorId);

    Set<TvShow> findByActorId(Long actorId);


}
