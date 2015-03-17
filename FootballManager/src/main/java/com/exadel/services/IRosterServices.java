package com.exadel.services;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Roster;
import com.exadel.orm.Teams;

/**
 * @author st02
 * 
 */
public interface IRosterServices {
    /**
     * save roster
     * 
     * @param roster
     */
    public void saveRoster(Roster roster);

    /**
     * update roster
     * 
     * @param roster
     */
    public void updateRoster(Roster roster);

    /**
     * delete roster
     * 
     * @param roster
     */
    public void deleteRoster(Roster roster);

    /**
     * @param playerID
     * @param teamID
     * @return roster by player's id and team
     */
    public Roster getRoster(int playerID, int teamID);
    /**
     * @param league
     */
    public void clearLeagueRosters(Leagues league);
    
    /**
     * @return list of players
     */
    public List<Roster> getTeamRoster(Teams team);
}
