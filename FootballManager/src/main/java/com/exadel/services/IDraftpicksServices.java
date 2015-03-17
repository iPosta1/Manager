package com.exadel.services;

import java.util.List;

import com.exadel.orm.Draftpicks;
import com.exadel.orm.Leagues;

public interface IDraftpicksServices {

        public void saveDraftpick(Draftpicks drq);
        /**
        * @param drq
        */
       public void updateDraftpick(Draftpicks drq);
        /**
        * @param drq
        */
       public void deleteDraftpick(Draftpicks drq);
       
       /**
        * @param league
        * @return list of teams with draft round and pick
        */
       public List<Draftpicks> getQueue(Leagues league);
}
