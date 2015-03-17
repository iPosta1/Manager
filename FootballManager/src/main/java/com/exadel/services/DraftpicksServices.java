package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IDraftpicksDao;
import com.exadel.orm.Draftpicks;
import com.exadel.orm.Leagues;

@Service("DraftpicksServices")
public class DraftpicksServices implements IDraftpicksServices {

    @Autowired
    private IDraftpicksDao drpDao;
    
    public void setDao(IDraftpicksDao dao){
        this.drpDao = dao;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveDraftpick(Draftpicks drq) {
        drpDao.save(drq);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateDraftpick(Draftpicks drq) {
        drpDao.update(drq);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteDraftpick(Draftpicks drq) {
        drpDao.delete(drq);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Draftpicks> getQueue(Leagues league) {
        return drpDao.getQueue(league);
    }
}
