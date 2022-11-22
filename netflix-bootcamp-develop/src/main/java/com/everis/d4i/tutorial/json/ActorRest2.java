package com.everis.d4i.tutorial.json;


import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;

import java.util.Set;

public class ActorRest2 {


    private static final long serialVersionUID = 1L;

    private Long actorId;

    private String name;

    private short age;

    private String nationality;

    private String shortDescription;

    private Set<ChapterRest> chapterList;

    private Set<TvShowRest> tvShowRestsList;


    public ActorRest2() {
    }

    public ActorRest2(Long actorId, String name, short age, String nationality, String shortDescription, Set<ChapterRest> chapterList, Set<TvShowRest> tvShowRestsList) {
        this.actorId = actorId;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.shortDescription = shortDescription;
        this.chapterList = chapterList;
        this.tvShowRestsList = tvShowRestsList;
    }


    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Set<ChapterRest> getChapterList() {
        return chapterList;
    }

    public void setChapterList(Set<ChapterRest> chapterList) {
        this.chapterList = chapterList;
    }

    public Set<TvShowRest> getTvShowRestsList() {
        return tvShowRestsList;
    }

    public void setTvShowRestsList(Set<TvShowRest> tvShowRestsList) {
        this.tvShowRestsList = tvShowRestsList;
    }
}
