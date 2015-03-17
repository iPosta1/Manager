package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Players;

/**
 * @author st02
 *
 */
public class PlayersDao extends HibernateDaoSupport implements IPlayersDao {

    /**
     * @see com.exadel.dao.IPlayersDao#save(com.exadel.orm.Players)
     */
    public void save(Players player) {
        getHibernateTemplate().save(player);
        
    }

    /**
     * @see com.exadel.dao.IPlayersDao#update(com.exadel.orm.Players)
     */
    public void update(Players player) {
        getHibernateTemplate().update(player);
    }

    /**
     * @see com.exadel.dao.IPlayersDao#delete(com.exadel.orm.Players)
     */
    public void delete(Players player) {
        getHibernateTemplate().delete(player);
    }

    /**
     * @see com.exadel.dao.IPlayersDao#getPlayer(int)
     */
    @SuppressWarnings("unchecked")
    public Players getPlayer(int id) {
        List<Players> player = getHibernateTemplate().find(
                "from Players p where p.playerID = " + id + "");
        return player.get(0);
    }

    /**
     * @see com.exadel.dao.IPlayersDao#getAllPlayers()
     */
    @SuppressWarnings("unchecked")
    public List<Players> getAllPlayers() {
        List<Players> players = getHibernateTemplate().find("from Players");
        return players;
    }

}
