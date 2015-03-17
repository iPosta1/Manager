package com.exadel.services;

import java.util.List;

import com.exadel.orm.Leagues;

/**
 * @author st02
 * 
 */
public interface ILeaguesServices {
    /**
     * @param league
     */
    public void saveLeague(Leagues league);

    /**
     * @param league
     */
    public void updateLeague(Leagues league);

    /**
     * @param league
     */
    public void deleteLeague(Leagues league);

    /**
     * @param name
     * @return league by name
     */
    public Leagues getLeaguebyName(String name);

    /**
     * @return all leagues
     */
    public List<Leagues> getAllLeagues();
    
    /**
     * @param username
     * @return list of leagues
     */
    public List<Leagues> getUsersLeagues(String username);
}
