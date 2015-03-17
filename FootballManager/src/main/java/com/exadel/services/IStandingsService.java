package com.exadel.services;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;

/**
 * @author st02
 * 
 */
public interface IStandingsService {
    /**
     * save standing
     * 
     * @param standing
     */
    public void saveStanding(Standings standing);

    /**
     * update standing
     * 
     * @param standing
     */
    public void updateStanding(Standings standing);

    /**
     * delete standing
     * 
     * @param standing
     */
    public void deleteStanding(Standings standing);

    /**
     * @param teamID
     * @return standing by team
     */
    public Standings getStanding(int teamID);

    /**
     * @param number
     * @param leagueID
     * @return standing by number in league
     */
    public Standings getStandingByNumber(int number, int leagueID);
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
