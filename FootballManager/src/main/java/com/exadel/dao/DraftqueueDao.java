package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Draftqueue;
import com.exadel.orm.Leagues;

public class DraftqueueDao extends HibernateDaoSupport implements IDraftqueueDao {

	/**
	 * @see com.exadel.dao.IDraftqueueDao#save(com.exadel.orm.Draftqueue)
	 */
	public void save(Draftqueue drq) {
		 getHibernateTemplate().save(drq);
	}

	/**
	 * @see com.exadel.dao.IDraftqueueDao#update(com.exadel.orm.Draftqueue)
	 */
	public void update(Draftqueue drq) {
		 getHibernateTemplate().update(drq);	
	}

	/**
	 * @see com.exadel.dao.IDraftqueueDao#delete(com.exadel.orm.Draftqueue)
	 */
	public void delete(Draftqueue drq) {
		 getHibernateTemplate().delete(drq);
	}

	/**
	 * @see com.exadel.dao.IDraftqueueDao#getQueue(com.exadel.orm.Leagues)
	 */
	@SuppressWarnings("unchecked")
	public List<Draftqueue> getQueue(Leagues league) {
		 List<Draftqueue> drq = getHibernateTemplate().find(
	                "from Draftqueue d where d.draftqueueID.team.lleague.leagueID = "+league.getLeagueID()+" order by d.draftqueueID.pick ASC");
	        return drq;
	}


}
