package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Embeddable
public class StandingsID implements Serializable {

    private static final long serialVersionUID = 1L;
    // PK consists of 2 FK: Player id and Team id

    // one standing in one league
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LeagueID")
    @JsonManagedReference
    private Leagues league;

    // one standing for one team
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamID")
    @JsonManagedReference
    private Teams team;

    // getters and setters
    public void setLeague(Leagues league) {
        this.league = league;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Leagues getLeague() {
        return league;
    }

    public Teams getTeam() {
        return team;
    }

}