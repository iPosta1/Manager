package com.exadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.ILplayersDao;
import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;

@Service("LplayersServices")
public class LplayersServices implements ILplayersServices{

    
    @Autowired
    private ILplayersDao llpDao;

    public void setDao(ILplayersDao dao) {
        this.llpDao = dao;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)    
    public void savePlayer(Lplayers player) {
        llpDao.save(player);
        
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePlayer(Lplayers player) {
        llpDao.update(player);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePlayer(Lplayers player) {
        llpDao.delete(player);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Lplayers getLplayer(int id) {
        return llpDao.getLplayer(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Lplayers> getAllLeaguePlayers(Leagues league) {
        
        return llpDao.getAllLeaguePlayers(league);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Lplayers> getAllDraftPlayers(Leagues league) {
    	  
        return llpDao.getAllDraftPlayers(league);
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Lplayers playerToDraft(List<String> positions, Leagues league) {
		return llpDao.playerToDraft(positions, league);
	}

}
