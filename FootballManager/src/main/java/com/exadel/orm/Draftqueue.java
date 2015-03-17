package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;



@Entity
@Table
@AssociationOverride(name = "draftqueueID.team", joinColumns = @JoinColumn(name = "teamID")) 
public class Draftqueue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DraftqueueID draftqueueID;
	
	 @Column(name = "is_online")
	 private int isOnline;
	
	public void setDraftqueueID(DraftqueueID drq){
		this.draftqueueID=drq;
	}
	
	public DraftqueueID getDraftqueueID(){
		return draftqueueID;
	}

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }
}

