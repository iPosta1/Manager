package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IGamesDao;
import com.exadel.orm.Games;
import com.exadel.orm.Teams;
import com.exadel.orm.Weeks;

/**
 * @author st02
 * 
 */
@Service("GamesServices")
public class GamesServices implements IGamesServices {

    @Autowired
    private IGamesDao gamesDao;

    public void setDao(IGamesDao dao) {
        this.gamesDao = dao;
    }

    /**
     * @see com.exadel.services.IGamesServices#saveGame(com.exadel.orm.Games)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveGame(Games game) {
        gamesDao.save(game);
    }

    /**
     * @see com.exadel.services.IGamesServices#updategame(com.exadel.orm.Games)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updategame(Games game) {
        gamesDao.update(game);
    }

    /**
     * @see com.exadel.services.IGamesServices#deleteGame(com.exadel.orm.Games)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteGame(Games game) {
        gamesDao.delete(game);
    }

    /**
     * @see com.exadel.services.IGamesServices#getGame(int)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Games getGame(int id) {
        return gamesDao.getGame(id);
    }

    /**
     * @see com.exadel.services.IGamesServices#getTeamGames(com.exadel.orm.Teams)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Games> getTeamGames(Teams team) {
        return gamesDao.getTeamGames(team);
    }

    /**
     * @see com.exadel.services.IGamesServices#getGameByTeamWeek(com.exadel.orm.Teams,
     *      com.exadel.orm.Weeks)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Games getGameByTeamWeek(Teams team, Weeks week) {
        return gamesDao.getGameByTeamWeek(team, week);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Games> getGamesByWeek(Weeks week) {
        return gamesDao.getGamesByWeek(week);
    }

}
