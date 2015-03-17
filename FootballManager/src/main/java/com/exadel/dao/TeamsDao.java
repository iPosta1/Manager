package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;
import com.exadel.orm.Teams;
import com.exadel.orm.Users;

/**
 * @author st02
 *
 */
public class TeamsDao extends HibernateDaoSupport implements ITeamsDao{

    /**
     * @see com.exadel.dao.ITeamsDao#save(com.exadel.orm.Teams)
     */
    public void save(Teams team) {
        getHibernateTemplate().save(team);
    }

    /**
     * @see com.exadel.dao.ITeamsDao#update(com.exadel.orm.Teams)
     */
    public void update(Teams team) {
        getHibernateTemplate().update(team);
    }

    /**
     * @see com.exadel.dao.ITeamsDao#delete(com.exadel.orm.Teams)
     */
    public void delete(Teams team) {
        getHibernateTemplate().delete(team);
    }

    /**
     * @see com.exadel.dao.ITeamsDao#getTeam(int)
     */
    @SuppressWarnings("unchecked")
    public Teams getTeam(int id) {
        List<Teams> team = getHibernateTemplate().find(
                "from Teams t where t.teamID = " + id + "");
        return team.get(0);
    }

    /**
     * @see com.exadel.dao.ITeamsDao#getAllTeams()
     */
    @SuppressWarnings("unchecked")
    public List<Teams> getAllTeams() {
        List<Teams> teams = getHibernateTemplate().find("from Teams");
        return teams;
    }

    /**
     * @see com.exadel.dao.ITeamsDao#getAllTeamsLeague(com.exadel.orm.Leagues)
     */
    @SuppressWarnings("unchecked")
    public List<Teams> getAllTeamsLeague(Leagues league) {
        List<Teams> teams = getHibernateTemplate().find("from Teams t where t.lleague = "+league.getLeagueID()+"");
        return teams;
    }

    /**
     * @see com.exadel.dao.ITeamsDao#getTeamBy(com.exadel.orm.Users, com.exadel.orm.Leagues)
     */
    @SuppressWarnings("unchecked")
    public Teams getTeamBy(Users user, Leagues league) {
        List<Teams> teams = getHibernateTemplate().find("from Teams t where t.lleague.leagueID = "+league.getLeagueID()+" and t.uuser.userID = "+user.getUserID()+"");
        return teams.get(0);
    }

    @SuppressWarnings("unchecked")
    public Teams getTeamByLeague(String name, Leagues league) {
        List<Teams> teams = getHibernateTemplate().find("from Teams t where t.lleague.leagueID = "+league.getLeagueID()+" and t.name = '"+name+"'");
        return teams.get(0);
    }

}
