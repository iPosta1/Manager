package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Players;

/**
 * @author st02
 *
 */
public interface IPlayersDao {
    /**
     * save player
     * @param player
     */
    public void save(Players player);
    /**
     * update player
     * @param player
     */
    public void update(Players player);
    /**
     * delete player
     * @param player
     */
    public void delete(Players player);
    /**
     * @param id
     * @return player by id
     */
    public Players getPlayer(int id);
    /**
     * @return all players
     */
    public List<Players> getAllPlayers();
}
