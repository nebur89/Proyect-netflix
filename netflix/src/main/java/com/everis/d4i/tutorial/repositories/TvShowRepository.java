
package com.everis.d4i.tutorial.repositories;

import java.util.List;
import java.util.Set;

import com.everis.d4i.tutorial.entities.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.TvShow;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {


	Set<TvShow> findByCategoryList_CategoryId(Long categoryId);













}
