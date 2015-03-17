package com.exadel.services;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Weeks;

/**
 * @author st02
 * 
 */
public interface IWeeksServices {
    /**
     * save week
     * 
     * @param week
     */
    public void saveWeek(Weeks week);

    /**
     * update week
     * 
     * @param week
     */
    public void updateWeek(Weeks week);

    /**
     * delete week
     * 
     * @param week
     */
    public void deleteWeek(Weeks week);

    /**
     * @param id
     * @return week by id
     */
    public Weeks getWeek(int id);

    /**
     * @param name
     * @param leagueID
     * @return get week by name in the league
     */
    public Weeks getWeekByName(String name, int leagueID);
    
    /**
     * @param league
     * @return list of weeks
     */
    public List<Weeks> getWeeksByLeague(Leagues league);
}
