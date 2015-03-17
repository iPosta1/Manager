package com.exadel.dao;
import java.util.List;

import com.exadel.orm.Leagues;

/**
 * @author st02
 *
 */
public interface ILeaguesDao {
    /**
     * save league
     * @param league
     */
    public void save(Leagues league);
    /**
     * update league
     * @param league
     */
    public void update(Leagues league);
    /**
     * delete league
     * @param league
     */
    public void delete(Leagues league);
    /**
     * @param name
     * @return league by name
     */
    public Leagues getLeagueByName(String name);
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
