package com.exadel.services;

import java.util.List;

import com.exadel.orm.Players;

/**
 * @author st02
 * 
 */
public interface IPlayersServices {
    /**
     * save player
     * 
     * @param player
     */
    public void savePlayer(Players player);

    /**
     * update player
     * 
     * @param player
     */
    public void updatePlayer(Players player);

    /**
     * delete player
     * 
     * @param player
     */
    public void deletePlayer(Players player);

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
