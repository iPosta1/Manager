package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Leagues;

/**
 * @author st02
 *
 */
public class LeaguesDao extends HibernateDaoSupport implements ILeaguesDao  {

    /**
     * @see com.exadel.dao.ILeaguesDao#save(com.exadel.orm.Leagues)
     */
    public void save(Leagues league) {
        getHibernateTemplate().save(league);
        
    }

    /**
     * @see com.exadel.dao.ILeaguesDao#update(com.exadel.orm.Leagues)
     */
    public void update(Leagues league) {
        getHibernateTemplate().update(league);
        
    }

    /**
     * @see com.exadel.dao.ILeaguesDao#delete(com.exadel.orm.Leagues)
     */
    public void delete(Leagues league) {
        getHibernateTemplate().delete(league);
        
    }

    /**
     * @see com.exadel.dao.ILeaguesDao#getLeagueByName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Leagues getLeagueByName(String name) {
        List<Leagues> leagues = getHibernateTemplate().find(
                "from Leagues l where l.leagueName = '" + name + "'");
        return leagues.get(0);
    }

    /**
     * @see com.exadel.dao.ILeaguesDao#getAllLeagues()
     */
    @SuppressWarnings("unchecked")
    public List<Leagues> getAllLeagues() {
        List<Leagues> leagues = getHibernateTemplate().find("from Leagues order by startDate desc");
        return leagues;
    }

    @SuppressWarnings("unchecked")
    public List<Leagues> getUsersLeagues(String username) {
      
   
 //select l.leagueID from Teams t inner join t.lleague l inner join t.uuser u where u.username = '" + username + "'"   
 //from Leagues l where l.leagueID = mass(i);
        // from Leagues l inner join l.leagueID
        
        /*
        List<Integer> leaguesIDs= new ArrayList<Integer>();
        leaguesIDs = getHibernateTemplate().find("select t.lleague from Teams t inner join t.uuser u where u.username = '" + username + "'" );
        List<Leagues> leagues = new ArrayList<Leagues>();
        for (int i=0;i<leaguesIDs.size();i++) {
         List<Leagues> league = getHibernateTemplate().find("from Leagues l where l.leagueID ="+leaguesIDs.get(i)+"");
         leagues.add(league.get(0));
        } */
        
        List<Leagues> leagues = getHibernateTemplate().find("Select l from Teams t inner join t.uuser u inner join t.lleague l where u.username = '" + username + "'");
        
        return leagues;
    }

}
