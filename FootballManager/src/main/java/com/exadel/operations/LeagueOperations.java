package com.exadel.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

import com.exadel.Offense;
import com.exadel.SimpleOperations;
import com.exadel.orm.Draftqueue;
import com.exadel.orm.DraftqueueID;
import com.exadel.orm.Games;
import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;
import com.exadel.orm.Players;
import com.exadel.orm.Standings;
import com.exadel.orm.StandingsID;
import com.exadel.orm.Teams;
import com.exadel.orm.Weeks;
import com.exadel.services.IDraftqueueServices;
import com.exadel.services.IGamesServices;
import com.exadel.services.ILeaguesServices;
import com.exadel.services.ILplayersServices;
import com.exadel.services.IPlayersServices;
import com.exadel.services.IStandingsService;
import com.exadel.services.ITeamsServices;
import com.exadel.services.IUsersServices;
import com.exadel.services.IWeeksServices;

public class LeagueOperations {
    /** for logging */
    private static final Logger LOG = Logger.getLogger(Offense.class.getName());

    /**
     * This method starts the league. At first, method generates teams for bots
     * (if there are empty slots in league), then creates standings for bot's
     * teams. Then generates all games in the league, except postseason games.
     * 
     * @param leaguename
     * @return true or false
     */
    public static boolean startLeague(String leaguename, ILeaguesServices lservice,
            ITeamsServices tservice, IStandingsService sservice, IWeeksServices wservice,
            IGamesServices gservice, IUsersServices uservice, ILplayersServices lpservice,
            IPlayersServices pservice, IDraftqueueServices dservice) {

        try {

            Leagues league = lservice.getLeaguebyName(leaguename);

            if (league.getStarted().equals("y")) // if league also started
                return false;

            else {
                /* create teams for bots */
                TeamOperations.createBotsTeams(leaguename, tservice, lservice, sservice, uservice);

                List<Teams> fullTeamList = new ArrayList<Teams>();
                /* get full list of teams */
                fullTeamList = tservice.getAllTeamsLeague(league);

                /* shuffle list */
                Collections.shuffle(fullTeamList);

                /* generate places for teams in league */
                for (int i = 1; i < fullTeamList.size() + 1; i++) {

                    /* get standing by team */
                    Standings standing = sservice.getStanding(fullTeamList.get(i - 1).getTeamID());

                    standing.setTeamNumber(i); // set position in tournament
                                               // table
                    sservice.updateStanding(standing); // update
                }
                /* ------------------------------------- */

                /* shuffle list again */
                Collections.shuffle(fullTeamList);

                /* generate draft position for teams in league */
                for (int i = 1; i < fullTeamList.size() + 1; i++) {
                    /* get standing by team */
                    Standings standing = sservice.getStanding(fullTeamList.get(i - 1).getTeamID());

                    standing.setDraftNumber(i); // set position in tournament
                                                // table
                    sservice.updateStanding(standing); // update
                }
                /* -------------------------------------------- */

                /**
                 * generate schedule
                 * 
                 * @see com.exadel.SimpleOperations#getAllGames(int)
                 * */
                for (int i = 0; i < fullTeamList.size(); i++) {
                    Standings teamStanding = sservice.getStanding(fullTeamList.get(i).getTeamID()); // get
                                                                                                    // team
                                                                                                    // position
                    /* generate list of rivals */
                    List<Integer> fullGamesList = SimpleOperations.getAllGames(teamStanding
                            .getTeamNumber());

                    /* create games for each team */
                    for (int j = 0; j < fullGamesList.size(); j++) {
                        try {
                            Games game = new Games();
                            game.setTeam1(fullTeamList.get(i));
                            Standings team2st = new Standings();
                            /* get rival by position */
                            team2st = sservice.getStandingByNumber(fullGamesList.get(j),
                                    league.getLeagueID());

                            StandingsID sid = team2st.getSID(); // get second
                                                                // team ID
                            sid.getTeam();
                            game.setTeam2(sid.getTeam());

                            /* set week for game */
                            Weeks week = new Weeks();
                            week = wservice.getWeekByName("week " + (j + 1), league.getLeagueID());

                            game.setWeek(week);// set week id
                            game.setGameTime(week.getGameDate()); // set game
                                                                  // date
                            gservice.saveGame(game); // save game

                        } catch (Exception e) {
                           // LOG.log(Level.WARNING, "game exists ");
                            // skip existing games (team1 vs team2 = team2 vs
                            // team1)
                            // LOG.log(Level.SEVERE, "Exception: ", e);
                        }
                    }
                }
                /* create players for league in Lplayers */
                List<Players> players = new ArrayList<Players>();
                players = pservice.getAllPlayers();
                for (int i = 0; i < players.size(); i++) {
                    Lplayers newplayer = new Lplayers();

                    newplayer.setPplayer(players.get(i));
                    newplayer.setPleague(league);
                    newplayer.setStamina(100);

                    lpservice.savePlayer(newplayer);
                }
                /*-----------------------------------*/

                /* create draft queue */
                List<Standings> standings = new ArrayList<Standings>();
                // get all standings ordered by draft position
                standings = sservice.getDraftQueue(league);

                /*---- generate draft queue ------*/
                // 10 rounds
                for (int round = 1; round < 21; round++) {
                    for (int pick = 0; pick < standings.size(); pick++) {
                        Draftqueue drq = new Draftqueue();
                        DraftqueueID drqID = new DraftqueueID();
                        drqID.setRound(round);
                        //
                        drqID.setPick(32 * (round - 1) + pick+1);
                        drqID.setTeam(standings.get(pick).getSID().getTeam());

                        drq.setDraftqueueID(drqID);
                        dservice.saveQueue(drq);
                    }
                }
                /*----------------------------*/

                league.setStarted("y"); // start league if only all games
                                        // created
                lservice.updateLeague(league);

                return true;

            }

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return false;
    }
}
