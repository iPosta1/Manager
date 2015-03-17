package com.exadel.services;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Teams;
import com.exadel.orm.Users;

/**
 * @author st02
 * 
 */
public interface ITeamsServices {
    /**
     * save team
     * 
     * @param team
     */
    public void saveTeam(Teams team);

    /**
     * update team
     * 
     * @param team
     */
    public void updateTeam(Teams team);

    /**
     * delete team
     * 
     * @param team
     */
    public void deleteTeam(Teams team);

    /**
     * @param id
     * @return team by id
     */
    public Teams getTeam(int id);

    /**
     * @return all teams
     */
    public List<Teams> getAllTeams();

    /**
     * @param league
     * @return all teams in the league
     */
    public List<Teams> getAllTeamsLeague(Leagues league);

    /**
     * @param user
     * @param league
     * @return user's team in the league
     */
    public Teams getTeamBy(Users user, Leagues league);
    /**
     * @param name
     * @param league
     * @return team by name and league
     */
    public Teams getTeamByLeague(String name,Leagues league);
}
