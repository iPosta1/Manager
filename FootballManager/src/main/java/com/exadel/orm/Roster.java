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
        @AssociationOverride(name = "rosterID.team", joinColumns = @JoinColumn(name = "teamID")),
        @AssociationOverride(name = "rosterID.lplayerID", joinColumns = @JoinColumn(name = "lplayerID")) })
public class Roster implements Serializable {

    private static final long serialVersionUID = 1L;
    /* private key consist of two FK */
    @EmbeddedId
    // RoserID.class
    private RosterID rosterID;
    /*----------*/
    @Column(name = "position", length = 10)
    private String position;
    /*----------*/
    @Column(name = "position_number")
    private int positionNumber;

    /*----------*/

    /* setters */
    public void setRosterID(RosterID rosterID) {
        this.rosterID = rosterID;
    }

    public void setPosition(String pos) {
        this.position = pos;
    }

    public void setPositionNumber(int number) {
        this.positionNumber = number;
    }

    /* getters */
    public RosterID getRosterID() {
        return rosterID;
    }

    public String getPosition() {
        return position;
    }

    public int getPositionNumber() {
        return positionNumber;
    }

}