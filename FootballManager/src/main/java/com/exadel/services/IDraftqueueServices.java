package com.exadel.services;

import java.util.List;

import com.exadel.orm.Draftqueue;
import com.exadel.orm.Leagues;

public interface IDraftqueueServices {
	 public void saveQueue(Draftqueue drq);
	 /**
	 * @param drq
	 */
	public void updateQueue(Draftqueue drq);
	 /**
	 * @param drq
	 */
	public void deleteQueue(Draftqueue drq);
	
	/**
	 * @param league
	 * @return list of teams with draft round and pick
	 */
	public List<Draftqueue> getQueue(Leagues league);
}
