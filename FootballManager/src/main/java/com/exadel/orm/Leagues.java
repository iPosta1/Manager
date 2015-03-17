package com.exadel.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Leagues implements Serializable {

    private static final long serialVersionUID = 1L;
    /* private key */
    @Id
    @Column(name = "leagueID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private int leagueID;
    /*--------------*/
    @Column(name = "league_name", nullable = false, length = 30)
    private String leagueName;
    /*--------------*/
    @Column(name = "primetime", nullable = false)
    private Date primetime;
    /*--------------*/
    @Column(name = "maxplayers", nullable = false)
    private int maxPlayers;
    /*--------------*/
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    /*--------------*/
    @Column(name = "is_started", nullable = false)
    private String started;
    /*--------------*/
    // relations, one league - many teams
    @OneToMany(mappedBy = "lleague", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Teams> teams;

    // relations, one league - many weeks
    @OneToMany(mappedBy = "wleague", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Weeks> weeks;

    // relations, one league - many league's players
    @OneToMany(mappedBy = "pleague", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Lplayers> pplayers;

    // -----------

    /* setters */
    public void setLeagueID(int id) {
        this.leagueID = id;
    }

    public void setLeagueName(String name) {
        this.leagueName = name;
    }

    public void setPrimetime(Date date2) {

        this.primetime = date2;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }

    public void setWeeks(Set<Weeks> weeks) {
        this.weeks = weeks;
    }

    public void setStarted(String start) {
        this.started = start;
    }

    /* getters */

    public int getLeagueID() {
        return leagueID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public Date getPrimetime() {

        return primetime;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public Set<Weeks> getWeeks() {
        return weeks;
    }

    public String getStarted() {
        return started;
    }

    public List<Lplayers> getPplayers() {
        return pplayers;
    }

    public void setPplayers(List<Lplayers> pplayers) {
        this.pplayers = pplayers;
    }
}
