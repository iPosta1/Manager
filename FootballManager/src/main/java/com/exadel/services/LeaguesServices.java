package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.ILeaguesDao;
import com.exadel.orm.Leagues;

/**
 * @author st02
 *
 */
@Service("LeaguesServices")
@Transactional(readOnly = false)
public class LeaguesServices implements ILeaguesServices {
   
    @Autowired
    private ILeaguesDao leaguesDao;

    public void setDao(ILeaguesDao dao) {
        this.leaguesDao = dao;
    }
    
    /**
     * @see com.exadel.services.ILeaguesServices#saveLeague(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveLeague(Leagues league) {
        leaguesDao.save(league);
        
    }

    /**
     * @see com.exadel.services.ILeaguesServices#updateLeague(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateLeague(Leagues league) {
        leaguesDao.update(league);
        
    }

    /**
     * @see com.exadel.services.ILeaguesServices#deleteLeague(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteLeague(Leagues league) {
        leaguesDao.delete(league);
        
    }

    /**
     * @see com.exadel.services.ILeaguesServices#getLeaguebyName(java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Leagues getLeaguebyName(String name) {
        Leagues leag = leaguesDao.getLeagueByName(name);
        return leag;
    }
    
    /**
     * @see com.exadel.services.ILeaguesServices#getAllLeagues()
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Leagues> getAllLeagues() {
        return leaguesDao.getAllLeagues();
    }

    /**
     * @see com.exadel.services.ILeaguesServices#getUsersLeagues(java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Leagues> getUsersLeagues(String username) {
        return leaguesDao.getUsersLeagues(username);
    }
}
