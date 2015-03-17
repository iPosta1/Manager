package com.exadel.orm;

/**
 * @author st02
 * 
 */
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*Lplayers - League players, for each league there is own list of players */
@Entity
@Table
public class Lplayers implements Serializable {

    private static final long serialVersionUID = 1L;
    /* private key */
    @Id
    @Column(name = "lplayerID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int lplayerID;
    /*--------------*/
    @Column(name = "stamina")
    private int stamina;
    /*--------------*/

    // many league players - one player
    @ManyToOne(targetEntity = Players.class)
    @JoinColumn(name = "playerID")
    @JsonManagedReference
    private Players pplayer; // mapped

    // many players - one league
    @ManyToOne(targetEntity = Leagues.class)
    @JoinColumn(name = "leagueID")
    @JsonManagedReference
    private Leagues pleague; // mapped

    // many players - one team
    @ManyToOne(targetEntity = Teams.class)
    @JoinColumn(name = "teamID")
    @JsonManagedReference
    private Teams pteam; // mapped
    
    
    //one team - many players
    @OneToMany(mappedBy = "lpplayer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Draftpicks> draftpicks;

    /* setters */

    public void setLplayerID(int id) {
        this.lplayerID = id;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setPplayer(Players pl) {
        this.pplayer = pl;
    }

    public void setPleague(Leagues league) {
        this.pleague = league;
    }

    public void setPteam(Teams team) {
        this.pteam = team;
    }

    /* getters */

    public int getLplayerID() {
        return lplayerID;
    }

    public int getStamina() {
        return stamina;
    }

    public Leagues getPleague() {
        return pleague;
    }

    public Players getPplayer() {
        return pplayer;
    }

    public Teams getPteam() {
        return pteam;
    }
}
