package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IWeeksDao;
import com.exadel.orm.Leagues;
import com.exadel.orm.Weeks;

/**
 * @author st02
 *
 */

@Service("WeeksServices")
@Transactional(readOnly = true)
public class WeeksServices implements IWeeksServices{
    
    @Autowired
    private IWeeksDao weeksDao;
    
    /**
     * @param dao
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void setDao(IWeeksDao dao) {
        this.weeksDao = dao;
    }
    
    /**
     * @see com.exadel.services.IWeeksServices#saveWeek(com.exadel.orm.Weeks)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveWeek(Weeks week) {
        weeksDao.save(week);
    }

    /**
     * @see com.exadel.services.IWeeksServices#updateWeek(com.exadel.orm.Weeks)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateWeek(Weeks week) {
        weeksDao.update(week);
    }

    /**
     * @see com.exadel.services.IWeeksServices#deleteWeek(com.exadel.orm.Weeks)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteWeek(Weeks week) {
        weeksDao.delete(week);
    }

    /**
     * @see com.exadel.services.IWeeksServices#getWeek(int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Weeks getWeek(int id) {
        return weeksDao.getWeek(id);
    }

    /**
     * @see com.exadel.services.IWeeksServices#getWeekByName(java.lang.String, int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Weeks getWeekByName(String name, int leagueID) {
        return weeksDao.getWeekByName(name,leagueID);
    }

    /**
     * @see com.exadel.services.IWeeksServices#getWeeksByLeague(com.exadel.orm.Leagues)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Weeks> getWeeksByLeague(Leagues league) {
        return weeksDao.getWeeksByLeague(league);
    }

}
