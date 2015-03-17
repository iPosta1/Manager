package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Roster;
import com.exadel.orm.Teams;

/**
 * @author st02
 *
 */
public interface IRosterDao {
    /**
     * save player in team's roster
     * @param roster
     */
    public void save(Roster roster);
    /**
     * update player in team's roster
     * @param roster
     */
    public void update(Roster roster);
    /**
     * delete player in team's roster
     * @param roster
     */
    public void delete(Roster roster);
    /**
     * @param playerID
     * @param teamID
     * @return team's roster
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
