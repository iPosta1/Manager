package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IDraftqueueDao;
import com.exadel.orm.Draftqueue;
import com.exadel.orm.Leagues;

@Service("DraftqueueServices")
public class DraftqueueServices implements IDraftqueueServices{

	 @Autowired
	 private IDraftqueueDao drqDao;
	 
	 public void setDao(IDraftqueueDao dao){
		 this.drqDao = dao;
	 }
	
	 /**
	 * @see com.exadel.services.IDraftqueueServices#saveQueue(com.exadel.orm.Draftqueue)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveQueue(Draftqueue drq) {
		 drqDao.save(drq);
	}

	 /**
	 * @see com.exadel.services.IDraftqueueServices#updateQueue(com.exadel.orm.Draftqueue)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateQueue(Draftqueue drq) {
		 drqDao.update(drq);
	}

	 /**
	 * @see com.exadel.services.IDraftqueueServices#deleteQueue(com.exadel.orm.Draftqueue)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteQueue(Draftqueue drq) {
		 drqDao.delete(drq);
	}

	 /**
	 * @see com.exadel.services.IDraftqueueServices#getQueue(com.exadel.orm.Leagues)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Draftqueue> getQueue(Leagues league) {
		 return drqDao.getQueue(league);
	}

}
