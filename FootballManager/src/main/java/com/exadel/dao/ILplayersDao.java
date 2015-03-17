package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;



public interface ILplayersDao {
    /**
     * save player
     * @param player
     */
    public void save(Lplayers player);
    /**
     * update player
     * @param player
     */
    public void update(Lplayers player);
    /**
     * delete player
     * @param player
     */
    public void delete(Lplayers player);
    /**
     * @param id
     * @return player by id
     */
    public Lplayers getLplayer(int id);
    /**
     * @return all players in a league
     */
    public List<Lplayers> getAllLeaguePlayers(Leagues league);
    
    /**
     * @param league
     * @return all players, which are not drafted
     */
    public List<Lplayers> getAllDraftPlayers(Leagues league);
    
    /**
     * @param positions
     * @param league
     * @return top lplayer to draft depending on position
     */
    public Lplayers playerToDraft(List<String> positions, Leagues league);
}
