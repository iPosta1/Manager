package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;

/**
 * @author st02
 *
 */
public interface IStandingsDao {
    /**
     * save standing
     * @param standing
     */
    public void save(Standings standing);
    /**
     * update standing
     * @param standing
     */
    public void update(Standings standing);
    /**
     * delete standing
     * @param standing
     */
    public void delete(Standings standing);
    /**
     * @param teamID
     * @return standing by team's id
     */
    public Standings getStanding(int teamID);
    /**
     * @param number
     * @param leagueID
     * @return standing by team's position in league
     */
    public Standings getStandingByNumber(int number,int leagueID);
     /**
     * @param league
     * @return list of standings in a league
     */
    public List<Standings> getStandingsByLeague(Leagues league);
    
    /**
     * @param league
     * @return list of standings order by draft queue
     */
    public List<Standings> getDraftQueue(Leagues league);
}
