package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IPlayersDao;
import com.exadel.orm.Players;

/**
 * @author st02
 *
 */

@Service("PlayersServices")
public class PlayersServices implements IPlayersServices {
    private IPlayersDao playersDao;

    @Autowired
    public void setDao(IPlayersDao dao) {
        this.playersDao = dao;
    }

    /**
     * @see com.exadel.services.IPlayersServices#savePlayer(com.exadel.orm.Players)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePlayer(Players player) {
       playersDao.save(player);
    }
    
    /**
     * @see com.exadel.services.IPlayersServices#updatePlayer(com.exadel.orm.Players)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePlayer(Players player) {
        playersDao.update(player);
    }
    
    /**
     * @see com.exadel.services.IPlayersServices#deletePlayer(com.exadel.orm.Players)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePlayer(Players player) {
        playersDao.delete(player);
    }
    
    /**
     * @see com.exadel.services.IPlayersServices#getPlayer(int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Players getPlayer(int id) {
        
        return playersDao.getPlayer(id);
    }
    
    /**
     * @see com.exadel.services.IPlayersServices#getAllPlayers()
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Players> getAllPlayers() {
        return playersDao.getAllPlayers();
    }

}
