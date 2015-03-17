package com.exadel.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class DraftqueueID implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Teams team;
    
    
    @Column(name = "round", nullable = false)
    private int round;
    
    @Column(name = "pick", nullable = false)
    private int pick;

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPick() {
		return pick;
	}

	public void setPick(int pick) {
		this.pick = pick;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

}
