package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.ITeamsDao;
import com.exadel.orm.Leagues;
import com.exadel.orm.Teams;
import com.exadel.orm.Users;

/**
 * @author st02
 *
 */

@Service("TeamsServices")
public class TeamsServices implements ITeamsServices{
    
    @Autowired
    private ITeamsDao teamsDao;

    public void setDao(ITeamsDao dao) {
        this.teamsDao = dao;
    }
    
    /**
     * @see com.exadel.services.ITeamsServices#saveTeam(com.exadel.orm.Teams)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTeam(Teams team) {
        teamsDao.save(team); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#updateTeam(com.exadel.orm.Teams)
     */
    public void updateTeam(Teams team) {
        teamsDao.update(team); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#deleteTeam(com.exadel.orm.Teams)
     */
    public void deleteTeam(Teams team) {
        teamsDao.delete(team); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#getTeam(int)
     */
    public Teams getTeam(int id) {
        
        return teamsDao.getTeam(id); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#getAllTeams()
     */
    public List<Teams> getAllTeams() {
        return teamsDao.getAllTeams(); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#getAllTeamsLeague(com.exadel.orm.Leagues)
     */
    public List<Teams> getAllTeamsLeague(Leagues league) {
        return teamsDao.getAllTeamsLeague(league); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#getTeamBy(com.exadel.orm.Users, com.exadel.orm.Leagues)
     */
    public Teams getTeamBy(Users user, Leagues league) {
        return teamsDao.getTeamBy(user, league); 
    }

    /**
     * @see com.exadel.services.ITeamsServices#getTeamByLeague(java.lang.String, com.exadel.orm.Leagues)
     */
    public Teams getTeamByLeague(String name, Leagues league) {
        return teamsDao.getTeamByLeague(name,league);
    }
    
    

}
