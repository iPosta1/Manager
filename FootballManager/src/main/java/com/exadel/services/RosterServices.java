package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IRosterDao;
import com.exadel.orm.Leagues;
import com.exadel.orm.Roster;
import com.exadel.orm.Teams;

/**
 * @author st02
 *
 */

@Service("RosterServices")
public class RosterServices implements IRosterServices {

    @Autowired
    private IRosterDao rosterDao;

    public void setDao(IRosterDao dao) {
        this.rosterDao = dao;
    }

    /**
     * @see com.exadel.services.IRosterServices#saveRoster(com.exadel.orm.Roster)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveRoster(Roster roster) {
        rosterDao.save(roster);
    }
    
    /**
     * @see com.exadel.services.IRosterServices#updateRoster(com.exadel.orm.Roster)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateRoster(Roster roster) {
        rosterDao.update(roster);
    }

    /**
     * @see com.exadel.services.IRosterServices#deleteRoster(com.exadel.orm.Roster)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteRoster(Roster roster) {
        rosterDao.delete(roster);
    }

    /**
     * @see com.exadel.services.IRosterServices#getRoster(int, int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Roster getRoster(int playerID, int teamID) {
        return  rosterDao.getRoster(playerID,teamID);
    }

    /**
     * @see com.exadel.services.IRosterServices#clearLeagueRosters(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void clearLeagueRosters(Leagues league) {
        rosterDao.clearLeagueRosters(league);
        
    }

    public List<Roster> getTeamRoster(Teams team) {
        // TODO Auto-generated method stub
        return  rosterDao.getTeamRoster(team);
    }

}
