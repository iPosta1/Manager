package com.exadel.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Games;
import com.exadel.orm.Teams;
import com.exadel.orm.Weeks;

/**
 * @author st02
 *
 */
public class GamesDao extends HibernateDaoSupport implements IGamesDao {

    /**
     * @see com.exadel.dao.IGamesDao#save(com.exadel.orm.Games)
     */
    public void save(Games game) {
        getHibernateTemplate().save(game);

    }

    /**
     * @see com.exadel.dao.IGamesDao#update(com.exadel.orm.Games)
     */
    public void update(Games game) {
        getHibernateTemplate().update(game);
    }

    /**
     * @see com.exadel.dao.IGamesDao#delete(com.exadel.orm.Games)
     */
    public void delete(Games game) {
        getHibernateTemplate().delete(game);
    }

    /**
     * @see com.exadel.dao.IGamesDao#getGame(int)
     */
    @SuppressWarnings("unchecked")
    public Games getGame(int id) {
        List<Games> game = getHibernateTemplate().find(
                "from Games g where g.gameID = " + id + "");
        return game.get(0);
    }

    /**
     * @see com.exadel.dao.IGamesDao#getTeamGames(com.exadel.orm.Teams)
     */
    @SuppressWarnings("unchecked")
    public List<Games> getTeamGames(Teams team) {
        List<Games> games1 = getHibernateTemplate().find(
                "from Games g where g.team1ID = " + team.getTeamID() + "");

        List<Games> games2 = getHibernateTemplate().find(
                "from Games g where g.team2ID = " + team.getTeamID() + "");
        List<Games> allgames = new ArrayList<Games>();
        allgames.addAll(games1);
        allgames.addAll(games2);
        return allgames;
    }

    /**
     * @see com.exadel.dao.IGamesDao#getGameByTeamWeek(com.exadel.orm.Teams, com.exadel.orm.Weeks)
     */
    @SuppressWarnings("unchecked")
    public Games getGameByTeamWeek(Teams team, Weeks week) {
        List<Games> games = getHibernateTemplate().find(
                "from Games g where g.team1ID = " + team.getTeamID()
                        + " and weekID = " + week.getWeekID() + "");
        return games.get(0);
    }

    /**
     * @see com.exadel.dao.IGamesDao#getGamesByWeek(com.exadel.orm.Weeks)
     */
    @SuppressWarnings("unchecked")
    public List<Games> getGamesByWeek(Weeks week) {
         List<Games> games = getHibernateTemplate().find(
                "from Games g where g.week.weekID = " + week.getWeekID() + "");
        return games;
    }

}
