package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;

/**
 * @author st02
 *
 */
public class StandingsDao extends HibernateDaoSupport implements IStandingsDao {

    /**
     * @see com.exadel.dao.IStandingsDao#save(com.exadel.orm.Standings)
     */
    public void save(Standings standing) {
        getHibernateTemplate().save(standing);
    }

    /**
     * @see com.exadel.dao.IStandingsDao#update(com.exadel.orm.Standings)
     */
    public void update(Standings standing) {
        getHibernateTemplate().update(standing);
    }

    /**
     * @see com.exadel.dao.IStandingsDao#delete(com.exadel.orm.Standings)
     */
    public void delete(Standings standing) {
        getHibernateTemplate().delete(standing);
    }

    /**
     * @see com.exadel.dao.IStandingsDao#getStanding(int)
     */
    @SuppressWarnings("unchecked")
    public Standings getStanding(int teamID) {
        List<Standings> st = getHibernateTemplate().find(
                "from Standings s where s.sID.team = " + teamID + "");
        return st.get(0);
    }

    /**
     * @see com.exadel.dao.IStandingsDao#getStandingByNumber(int, int)
     */
    @SuppressWarnings("unchecked")
    public Standings getStandingByNumber(int number, int leagueID) {
        List<Standings> st = getHibernateTemplate().find(
                "from Standings s where s.sID.league = " + leagueID + " and s.teamNumber = "+number+"");
        return st.get(0);
    }

    /**
     * @see com.exadel.dao.IStandingsDao#getStandingsByLeague(com.exadel.orm.Leagues)
     */
    @SuppressWarnings("unchecked")
    public List<Standings> getStandingsByLeague(Leagues league) {
        List<Standings> st = getHibernateTemplate().find(
                "from Standings s where s.sID.league.leagueID = " + league.getLeagueID() + " order by s.teamNumber ASC");
        return st;
    }

    @SuppressWarnings("unchecked")
    public List<Standings> getDraftQueue(Leagues league) {
        List<Standings> st = getHibernateTemplate().find(
                "from Standings s where s.sID.league.leagueID = " + league.getLeagueID() + " order by s.draftNumber ASC");
        return st;
    }

}
