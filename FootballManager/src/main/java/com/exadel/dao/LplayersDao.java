package com.exadel.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;

public class LplayersDao extends HibernateDaoSupport implements ILplayersDao {

	/**
	 * @see com.exadel.dao.ILplayersDao#save(com.exadel.orm.Lplayers)
	 */
	public void save(Lplayers player) {
		getHibernateTemplate().save(player);
	}

	/**
	 * @see com.exadel.dao.ILplayersDao#update(com.exadel.orm.Lplayers)
	 */
	public void update(Lplayers player) {
		getHibernateTemplate().update(player);
	}

	/**
	 * @see com.exadel.dao.ILplayersDao#delete(com.exadel.orm.Lplayers)
	 */
	public void delete(Lplayers player) {
		getHibernateTemplate().delete(player);
	}

	/**
	 * @see com.exadel.dao.ILplayersDao#getLplayer(int)
	 */
	@SuppressWarnings("unchecked")
	public Lplayers getLplayer(int id) {
		List<Lplayers> player = getHibernateTemplate().find(
				"from Lplayers p where p.lplayerID = " + id + "");
		return player.get(0);
	}

	/**
	 * @see com.exadel.dao.ILplayersDao#getAllLeaguePlayers(com.exadel.orm.Leagues)
	 */
	@SuppressWarnings("unchecked")
	public List<Lplayers> getAllLeaguePlayers(Leagues league) {
		List<Lplayers> players = getHibernateTemplate().find(
				"from Lplayers p where p.pleague.leagueID = "
						+ league.getLeagueID() + "");
		return players;
	}

	@SuppressWarnings("unchecked")
	public List<Lplayers> getAllDraftPlayers(Leagues league) {
		List<Lplayers> players = getHibernateTemplate().find(
				"from Lplayers p where p.pleague.leagueID = "
						+ league.getLeagueID() + " and p.pteam.teamID is null order by p.pplayer.ovr DESC");
	
		return players;
	}

	/**
	 * 
	 * @see com.exadel.dao.ILplayersDao#playerToDraft(java.util.List,
	 * com.exadel.orm.Leagues)
	 */
	@SuppressWarnings("unchecked")
	public Lplayers playerToDraft(List<String> positions, Leagues league) {
		List<Lplayers> players = new ArrayList<Lplayers>();
		for (int i = 0; i < positions.size(); i++) {
			List<Lplayers> player = getHibernateTemplate().find(
					"from Lplayers p where p.pleague.leagueID = "
							+ league.getLeagueID()
							+ " and p.pplayer.defaultPosition = '"+positions.get(i)+"' and p.pteam.teamID is null order by p.pplayer.ovr DESC limit 1");
			players.add(player.get(0));
			
		}
		
		Lplayers playerToDraft = new Lplayers();
		
		
		for (int i=0;i<players.size();i++){
			if (i == 0) playerToDraft = players.get(i);
			if (players.get(i).getPplayer().getOvr() > playerToDraft.getPplayer().getOvr() ){
				playerToDraft = players.get(i);
			}
		}
		return playerToDraft;
	}

}
