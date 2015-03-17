package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table
@AssociationOverrides({
    @AssociationOverride(name = "sID.league", joinColumns = @JoinColumn(name = "leagueID")),
    @AssociationOverride(name = "sID.team", joinColumns = @JoinColumn(name = "teamID")) })
public class Standings implements Serializable {
    private static final long serialVersionUID = 1L;
    /* private key consist of two FK */
    @EmbeddedId
   
    private StandingsID sID;
    
    @Column(name = "team_number")
    private int teamNumber;
    /*------------*/
    
    @Column(name = "draft_number")
    private int draftNumber;
    /*------------*/
    
    @Column(name = "wins")
    private int wins;
    /*------------*/
    @Column(name = "loses")
    private int loses;
    /*------------*/
    @Column(name = "ties")
    private int ties;
    /*------------*/
    
    /* setters */
    public void setSID (StandingsID sid){
        this.sID = sid;
    }
    public void setTeamNumber(int number){
        this.teamNumber = number;
    }
    public void setDraftNumber(int number){
        this.draftNumber = number;
    }
    public void setWins(int number){
        this.wins = number;
    }
    public void setLoses(int number){
        this.loses = number;
    }
    public void setTies(int number){
        this.ties = number;
    }
    /* getters */
    public StandingsID getSID(){
        return sID;
    }
    public int getTeamNumber(){
        return teamNumber;
    }
    public int getDraftNumber(){
        return draftNumber;
    }
    public int getWins(){
        return wins;
    }
    public int getLoses(){
        return loses;
    }
    public int getTies(){
        return ties;
    }
    
    
}
