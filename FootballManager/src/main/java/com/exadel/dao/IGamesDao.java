package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Games;
import com.exadel.orm.Teams;
import com.exadel.orm.Weeks;

/**
 * @author st02
 *
 */
public interface IGamesDao {
    /**
     * save game
     * @param game
     */
    public void save(Games game);
    /**
     * update game
     * @param game
     */
    public void update(Games game);
    /**
     * delete game
     * @param game
     */
    public void delete(Games game);
    /**
     * @param id
     * @return game by id
     */
    public Games getGame(int id);
    /**
     * @param team
     * @return all team games
     */
    public List<Games> getTeamGames(Teams team);
    /**
     * @param team
     * @param week
     * @return game of week
     */
    public Games getGameByTeamWeek(Teams team, Weeks week);
    
    /**
     * @param week
     * @return list of games 
     */
    public List<Games> getGamesByWeek(Weeks week);
}
