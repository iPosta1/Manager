package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Weeks;

/**
 * @author st02
 *
 */
public interface IWeeksDao {
    /**
     * save week
     * @param week
     */
    public void save(Weeks week);
    /**
     * update week
     * @param week
     */
    public void update(Weeks week);
    /**
     * delete week
     * @param week
     */
    public void delete(Weeks week);
    /**
     * @param id
     * @return week by id
     */
    public Weeks getWeek(int id);
    /**
     * @param name
     * @param leagueID
     * @return week by name in the league
     */
    public Weeks getWeekByName(String name, int leagueID);
    
    /**
     * @param league
     * @return list of weeks
     */
    public List<Weeks> getWeeksByLeague(Leagues league);
    

}
