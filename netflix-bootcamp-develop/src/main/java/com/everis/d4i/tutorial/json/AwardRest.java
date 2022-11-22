package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.TvShow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonIgnoreProperties("tvShowList")
public class AwardRest {


        private static final long serialVersionUID = 1L;


        private Long awardId;

        private String name;

       private Set<TvShow> tvShowList;


    public AwardRest() {}

    public AwardRest(Long awardId, String name) {
        this.awardId = awardId;
        this.name = name;
    }

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
