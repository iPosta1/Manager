package com.exadel.operations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;

import com.exadel.Offense;
import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;
import com.exadel.orm.StandingsID;
import com.exadel.orm.Teams;
import com.exadel.services.ILeaguesServices;
import com.exadel.services.IStandingsService;
import com.exadel.services.ITeamsServices;
import com.exadel.services.IUsersServices;

public class TeamOperations {
    /** for logging */
    private static final Logger LOG = Logger.getLogger(Offense.class.getName());

    /**
     * This static method creates teams for bots in the league according empty
     * slots
     * 
     * @param leaguename
     * @return true or false
     */
    public static boolean createBotsTeams(String leaguename, ITeamsServices tservice,
            ILeaguesServices lservice, IStandingsService sservice, IUsersServices uservice) {
        try {

            Leagues league = new Leagues();
            league = lservice.getLeaguebyName(leaguename);

            List<Teams> teamsList = new ArrayList<Teams>();
            teamsList = tservice.getAllTeamsLeague(league);

            int playersCount = teamsList.size();
            int botsCount = league.getMaxPlayers() - playersCount;
            
            /* get team logo for bots */
            byte[] image = null;
            try{
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("botslogo.png");    
            image = IOUtils.toByteArray(is);
            
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Exception: ", e);
            }

            /* create bot's teams */

            for (int i = 1; i < botsCount + 1; i++) {
                Teams newteam = new Teams(); // create new team
                newteam.setName("team " + i);
                /* set user by bot name. Each bot has name like primetimerivalry# */
                newteam.setUser(uservice.getUserByName("primetimerivalry" + i));
                // 1-32 - id's of bot users
                newteam.setLleague(league);
                
                newteam.setTeamlogo(image);
                
                tservice.saveTeam(newteam);

                Standings newstanding = new Standings(); // create new
                                                         // standing
                StandingsID sid = new StandingsID(); // generate id for
                                                     // standing
                sid.setLeague(league);
                sid.setTeam(newteam);
                newstanding.setSID(sid);
                sservice.saveStanding(newstanding); // save standing
                
            }
            /* bots creation end */
            return true;
           

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        } 
        return false;
    }

}
