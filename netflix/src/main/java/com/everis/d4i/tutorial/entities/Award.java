package com.everis.d4i.tutorial.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "AWARD")
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AWARD_ID")
    private Long awardId;

    @Column(name = "NAME", unique = true)
    private String name;

    //RELATION

    //TvShow
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "awardsList")
    private Set<TvShow> tvShowList;



    //BUILDER
    public Award() {}

    public Award(Long awardId) {
        this.awardId = awardId;
    }

    public Award(Long awardId, String name, Set<TvShow> tvShowList) {
        this.awardId = awardId;
        this.name = name;
        this.tvShowList = tvShowList;
    }

    //GET AND SET

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TvShow> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(Set<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }
}
