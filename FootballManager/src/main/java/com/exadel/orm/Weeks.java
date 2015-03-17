package com.exadel.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Weeks implements Serializable {
    private static final long serialVersionUID = 1L;
    /*----private key---*/
    @Id
    @Column(name = "weekID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int weekID;

    /*------------*/
    @Column(name = "week_name", nullable = false, length = 20)
    private String weekName;
    /*------------*/
    @Column(name = "game_date", nullable = false)
    private Date gameDate;
    /*------------*/

    // many week in one league
    @ManyToOne(targetEntity = Leagues.class)
    @JoinColumn(name = "leagueID")
    @JsonManagedReference
    private Leagues wleague; // mapped

    /* setters */

    public void setWeekID(int id) {
        this.weekID = id;
    }

    public void setWeekName(String name) {
        this.weekName = name;
    }

    public void setGameDate(Date date) {
        this.gameDate = date;
    }

    public void setLeague(Leagues league) {
        this.wleague = league;
    }

    /* getters */
    public int getWeekID() {
        return weekID;
    }

    public String getWeekName() {
        return weekName;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public Leagues getLeague() {
        return wleague;
    }

}