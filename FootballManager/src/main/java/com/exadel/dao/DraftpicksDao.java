package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Draftpicks;
import com.exadel.orm.Leagues;

public class DraftpicksDao extends HibernateDaoSupport implements IDraftpicksDao {

    /**
     * @see com.exadel.dao.IDraftpicksDao#save(com.exadel.orm.IDraftpicksDao)
     */
    public void save(Draftpicks drq) {
         getHibernateTemplate().save(drq);
    }

    /**
     * @see com.exadel.dao.IDraftpicksDao#update(com.exadel.orm.IDraftpicksDao)
     */
    public void update(Draftpicks drq) {
         getHibernateTemplate().update(drq);    
    }

    /**
     * @see com.exadel.dao.IDraftpicksDao#delete(com.exadel.orm.IDraftpicksDao)
     */
    public void delete(Draftpicks drq) {
         getHibernateTemplate().delete(drq);
    }

    /**
     * @see com.exadel.dao.IDraftpicksDao#getQueue(com.exadel.orm.Leagues)
     */
    @SuppressWarnings("unchecked")
    public List<Draftpicks> getQueue(Leagues league) {
         List<Draftpicks> drq = getHibernateTemplate().find(
                    "from Draftpicks d where d.draftqueueID.team.lleague.leagueID = "+league.getLeagueID()+" order by d.draftqueueID.pick ASC");
            return drq;
    }

}
