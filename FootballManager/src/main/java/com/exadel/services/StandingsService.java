package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IStandingsDao;
import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;

/**
 * @author st02
 *
 */

@Service("StandingsService")
public class StandingsService implements IStandingsService{
    
    @Autowired
    private IStandingsDao standingsDao;

    public void setDao(IStandingsDao dao) {
        this.standingsDao = dao;
    }
    
    /**
     * @see com.exadel.services.IStandingsService#saveStanding(com.exadel.orm.Standings)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveStanding(Standings standing) {
        standingsDao.save(standing); 
    }

    /**
     * @see com.exadel.services.IStandingsService#updateStanding(com.exadel.orm.Standings)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateStanding(Standings standing) {
        standingsDao.update(standing); 
    }

    /**
     * @see com.exadel.services.IStandingsService#deleteStanding(com.exadel.orm.Standings)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteStanding(Standings standing) {
        standingsDao.delete(standing); 
    }
    
    /**
     * @see com.exadel.services.IStandingsService#getStanding(int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Standings getStanding(int teamID) {
        return standingsDao.getStanding(teamID);
    }

    /**
     * @see com.exadel.services.IStandingsService#getStandingByNumber(int, int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Standings getStandingByNumber(int number, int leagueID) {
        return standingsDao.getStandingByNumber(number,leagueID);
    }

    /**
     * @see com.exadel.services.IStandingsService#getStandingsByLeague(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Standings> getStandingsByLeague(Leagues league) {
        return standingsDao.getStandingsByLeague(league);
    }

    /**
     * @see com.exadel.services.IStandingsService#getDraftQueue(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Standings> getDraftQueue(Leagues league) {
        return standingsDao.getDraftQueue(league);
    }

}
