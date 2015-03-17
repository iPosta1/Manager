package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;
import com.exadel.orm.Roster;
import com.exadel.orm.Teams;

/**
 * @author st02
 *
 */
public class RosterDao extends HibernateDaoSupport implements IRosterDao {

    /**
     * @see com.exadel.dao.IRosterDao#save(com.exadel.orm.Roster)
     */
    public void save(Roster roster) {
        getHibernateTemplate().save(roster);
    }

    /**
     * @see com.exadel.dao.IRosterDao#update(com.exadel.orm.Roster)
     */
    public void update(Roster roster) {
        getHibernateTemplate().update(roster);
    }

    /**
     * @see com.exadel.dao.IRosterDao#delete(com.exadel.orm.Roster)
     */
    public void delete(Roster roster) {
        getHibernateTemplate().delete(roster);
    }

    /**
     * @see com.exadel.dao.IRosterDao#getRoster(int, int)
     */
    @SuppressWarnings("unchecked")
    public Roster getRoster(int playerID,int teamID) {
        List<Roster> roster = getHibernateTemplate().find(
                "from Roster r where r.rosterID.teamID = " + teamID + " and r.rosterID.playerID = "+playerID);
        return roster.get(0);
    }

    public void clearLeagueRosters(Leagues league) {
        getHibernateTemplate().bulkUpdate("delete from Roster r where r.rosterID.teamID.getLleague().getLeagueID()="+league.getLeagueID());
        
    }

    /**
     * @see com.exadel.dao.IRosterDao#getTeamRoster(com.exadel.orm.Teams)
     */
    @SuppressWarnings("unchecked")
    public List<Roster> getTeamRoster(Teams team) {
        List<Roster> roster = getHibernateTemplate().find(
                "from Roster r where r.rosterID.team.teamID = " + team.getTeamID() + "");
        return roster;
    }



}
