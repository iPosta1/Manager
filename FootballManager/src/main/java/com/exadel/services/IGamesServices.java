package com.exadel.services;

import java.util.List;

import com.exadel.orm.Games;
import com.exadel.orm.Teams;
import com.exadel.orm.Weeks;

/**
 * @author st02
 * 
 */
public interface IGamesServices {
    /**
     * save game
     * 
     * @param game
     */
    public void saveGame(Games game);

    /**
     * update game
     * 
     * @param game
     */
    public void updategame(Games game);

    /**
     * delete game
     * 
     * @param game
     */
    public void deleteGame(Games game);

    /**
     * @param id
     * @return game by id
     */
    public Games getGame(int id);

    /**
     * @param team
     * @return team's games
     */
    public List<Games> getTeamGames(Teams team);

    /**
     * @param team
     * @param week
     * @return team's game on the week
     */
    public Games getGameByTeamWeek(Teams team, Weeks week);
    
    /**
     * @param week
     * @return list of games 
     */
    public List<Games> getGamesByWeek(Weeks week);
}
