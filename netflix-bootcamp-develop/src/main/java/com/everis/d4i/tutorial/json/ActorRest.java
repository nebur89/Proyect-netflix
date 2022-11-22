package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.Chapter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
@JsonIgnoreProperties("chapterList")
public class ActorRest {


    private Long actorId;

    private String name;

    private short age;

    private String nationality;

    private String shortDescription;

    private Set<Chapter> chapterList;



    public ActorRest() {}

    public ActorRest(Long actorId) {
        this.actorId = actorId;
    }

    public ActorRest(String name, short age, String nationality, String shortDescription) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.shortDescription = shortDescription;
    }

    public ActorRest(Long actorId, String name, short age, String nationality, String shortDescription) {
        this.actorId = actorId;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.shortDescription = shortDescription;
    }

    public ActorRest(Long actorId, String name, short age, String nationality, String shortDescription, Set<Chapter> chapterList) {
        this.actorId = actorId;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.shortDescription = shortDescription;
        this.chapterList = chapterList;
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

    public Set<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(Set<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
