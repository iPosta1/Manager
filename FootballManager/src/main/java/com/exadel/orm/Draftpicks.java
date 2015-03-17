package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author st02
 *
 */
@Entity
@Table
@AssociationOverride(name = "draftqueueID.team", joinColumns = @JoinColumn(name = "teamID")) 
public class Draftpicks implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private DraftqueueID draftqueueID;
    
    @ManyToOne(targetEntity = Lplayers.class)
    @JoinColumn(name = "lplayerID")
    @JsonManagedReference
    private Lplayers lpplayer; // mapped
    
    /* setters */
    public void setDraftqueueID(DraftqueueID drq){
        this.draftqueueID=drq;
    }
    
    public void setLpplayer(Lplayers lpplayer) {
        this.lpplayer = lpplayer;
    }
    
    /* getters */
    public DraftqueueID getDraftqueueID(){
        return draftqueueID;
    }

    public Lplayers getLpplayer() {
        return lpplayer;
    }

   
}

