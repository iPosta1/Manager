package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;
import com.exadel.orm.Weeks;

/**
 * @author st02
 *
 */
public class WeeksDao extends HibernateDaoSupport implements IWeeksDao{

    /**
     * @see com.exadel.dao.IWeeksDao#save(com.exadel.orm.Weeks)
     */
    public void save(Weeks week) {
        getHibernateTemplate().save(week);
    }

    /**
     * @see com.exadel.dao.IWeeksDao#update(com.exadel.orm.Weeks)
     */
    public void update(Weeks week) {
        getHibernateTemplate().update(week);
    }

    /**
     * @see com.exadel.dao.IWeeksDao#delete(com.exadel.orm.Weeks)
     */
    public void delete(Weeks week) {
        getHibernateTemplate().delete(week);
    }

    /**
     * @see com.exadel.dao.IWeeksDao#getWeek(int)
     */
    @SuppressWarnings("unchecked")
    public Weeks getWeek(int id) {
        List<Weeks> week = getHibernateTemplate().find(
                "from Weeks w where w.weekID = " + id + "");
        return week.get(0);
    }

    /**
     * @see com.exadel.dao.IWeeksDao#getWeekByName(java.lang.String, int)
     */
    @SuppressWarnings("unchecked")
    public Weeks getWeekByName(String name, int leagueID) {
        List<Weeks> week = getHibernateTemplate().find(
                "from Weeks w where w.wleague = " + leagueID + " and w.weekName ='"+name+"'");
        return week.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<Weeks> getWeeksByLeague(Leagues league) {
        List<Weeks> weeks = getHibernateTemplate().find(
                "from Weeks w where w.wleague.leagueID = " + league.getLeagueID() + "");
        return weeks;
    }

}
