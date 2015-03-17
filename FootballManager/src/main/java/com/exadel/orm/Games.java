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

@Entity
@Table
public class Games implements Serializable {

    private static final long serialVersionUID = 1L;
    /* private key */
    @Id
    @Column(name = "gameID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int gameID;
    /*--------------*/
    @Column(name = "team1_points", columnDefinition = "int default 0")
    private int team1Points;
    /*--------------*/
    @Column(name = "team2_points", columnDefinition = "int default 0")
    private int team2Points;
    /*--------------*/
    @Column(name = "attack_team", nullable = true)
    private int attackTeam;
    /*--------------*/
    @Column(columnDefinition = "int default 3600", name = "remaining_time")
    private int remainingTime;
    /*--------------*/
    @Column(name = "game_time")
    private Date gameTime;
    /*--------------*/
    @Column(name = "field_position")
    private int fieldPosition;
    /*--------------*/

    // many games - one team
    @ManyToOne(targetEntity = Teams.class)
    @JoinColumn(name = "team1ID")
    private Teams team1;

    // many game - one team
    @ManyToOne(targetEntity = Teams.class)
    @JoinColumn(name = "team2ID")
    private Teams team2;

    // many games on one week
    @ManyToOne(targetEntity = Weeks.class)
    @JoinColumn(name = "weekID")
    private Weeks week;

    /* ------setters------ */
    public void setWeek(Weeks week) {
        this.week = week;
    }

    public void setGameID(int id) {
        this.gameID = id;
    }

    public void setTeam1Points(int points) {
        this.team1Points = points;
    }

    public void setTeam2Points(int points) {
        this.team2Points = points;
    }

    public void setAttackTeam(int id) {
        this.attackTeam = id;
    }

    public void setRemainingTime(int time) {
        this.remainingTime = time;
    }

    public void setGameTime(Date date) {
        this.gameTime = date;
    }

    public void setFieldPosition(int pos) {
        this.fieldPosition = pos;
    }

    public void setTeam1(Teams team) {
        this.team1 = team;
    }

    public void setTeam2(Teams team) {
        this.team2 = team;
    }

    /* ------getters------ */
    public int getGameID() {
        return gameID;
    }

    public Teams getTeam1() {
        return team1;
    }

    public Teams getTeam2() {
        return team2;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public int getAttackTeam() {
        return attackTeam;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getFieldPosition() {
        return fieldPosition;
    }

    public Weeks getWeek() {
        return week;
    }

}
