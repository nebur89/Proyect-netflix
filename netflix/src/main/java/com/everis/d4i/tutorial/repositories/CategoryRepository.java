package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    boolean existsCategoryByName(String name);


}
