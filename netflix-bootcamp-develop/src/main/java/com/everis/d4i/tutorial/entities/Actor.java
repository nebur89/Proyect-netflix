


package com.everis.d4i.tutorial.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACTOR")
public class Actor implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;


    //**ATTRIBUTES *************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_ID")
    private Long actorId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE")
    private short age;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "SHORT_DESC")
    private String shortDescription;

    //***RELATION *****************************

    //Chapter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actorList")
    private Set<Chapter> chapterList;


    //**BUILDER *******************************

    public Actor() {}

    public Actor(Long actorId, String name, short age, String nationality, String shortDescription, Set<Chapter> chapterList) {
        this.actorId = actorId;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.shortDescription = shortDescription;
        this.chapterList = chapterList;
    }

    //***GET AND SET ****************************************


    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public short getAge() { return age; }

    public void setAge(short age) { this.age = age; }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getShortDescription() { return shortDescription; }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public Set<Chapter> getChapterList() { return chapterList;}

    public void setChapterList(Set<Chapter> chapterList) { this.chapterList = chapterList; }
}
