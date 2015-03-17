package com.exadel.dao;

import java.util.List;

import com.exadel.orm.Draftqueue;
import com.exadel.orm.Leagues;


/**
 * @author Nike
 *
 */
public interface IDraftqueueDao {

	 public void save(Draftqueue drq);
	 /**
	 * @param drq
	 */
	public void update(Draftqueue drq);
	 /**
	 * @param drq
	 */
	public void delete(Draftqueue drq);
	
	/**
	 * @param league
	 * @return list of teams with draft round and pick
	 */
	public List<Draftqueue> getQueue(Leagues league);
	
}
