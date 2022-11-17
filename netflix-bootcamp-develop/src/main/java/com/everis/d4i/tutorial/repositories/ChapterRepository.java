package com.everis.d4i.tutorial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {



	List<Chapter> findBySeason_TvShow_TvShowIdAndSeason_Number(Long tvShowId, short number);


	Optional<Chapter> findBySeason_TvShow_TvShowIdAndSeason_NumberAndNumber(Long tvShowId, short number, short number1);





}
