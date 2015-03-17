package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Draftpicks;
import com.exadel.orm.Leagues;



/**
 * @author st02
 *
 */
public interface IDraftpicksDao {

     public void save(Draftpicks drq);
     /**
     * @param drq
     */
    public void update(Draftpicks drq);
     /**
     * @param drq
     */
    public void delete(Draftpicks drq);
    
    /**
     * @param league
     * @return list of teams with draft round and pick
     */
    public List<Draftpicks> getQueue(Leagues league);
}
