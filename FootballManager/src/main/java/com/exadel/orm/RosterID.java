package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RosterID implements Serializable {

    private static final long serialVersionUID = 1L;
    // PK consists of 2 FK: Player id and Team id
    @ManyToOne
    private Lplayers lplayerID;
    @ManyToOne
    private Teams team;

    // getters and setters
    public void setLplayer(Lplayers player) {
        this.lplayerID = player;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Lplayers getLplayer() {
        return lplayerID;
    }

    public Teams getTeam() {
        return team;
    }

}