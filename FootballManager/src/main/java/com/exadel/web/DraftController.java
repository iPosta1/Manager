package com.exadel.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exadel.operations.DraftOperations;
import com.exadel.orm.Draftpicks;
import com.exadel.orm.Draftqueue;
import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;
import com.exadel.orm.Roster;
import com.exadel.orm.RosterID;
import com.exadel.orm.Teams;
import com.exadel.orm.Users;
import com.exadel.services.IDraftpicksServices;
import com.exadel.services.IDraftqueueServices;
import com.exadel.services.ILeaguesServices;
import com.exadel.services.ILplayersServices;
import com.exadel.services.IRosterServices;
import com.exadel.services.ITeamsServices;
import com.exadel.services.IUsersServices;

/**
 * @author st02
 * 
 */
@Controller
@RequestMapping("/draft")
public class DraftController {
    @Autowired
    private IUsersServices usersService;

    @Autowired
    private ILeaguesServices leaguesService;

    @Autowired
    private ITeamsServices teamsService;

    @Autowired
    private ILplayersServices lplayersService;

    @Autowired
    private IRosterServices rosterService;

    @Autowired
    private IDraftqueueServices draftqueueService;

    @Autowired
    private IDraftpicksServices draftpicksService;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    // for logging
    private static final Logger LOG = Logger.getLogger(DraftController.class.getName());

    // league's draftqueue
    Map<String, List<Draftqueue>> leaguequeue = new HashMap<String, List<Draftqueue>>();
    // league's draftpicks
    Map<String, List<Draftpicks>> leaguepicks = new HashMap<String, List<Draftpicks>>();
    // league's draftpicks count
    Map<String, Integer> leaguepickscount = new HashMap<String, Integer>();
    // league's players
    Map<String, Map<Integer, Lplayers>> leagueplayers = new HashMap<String, Map<Integer, Lplayers>>();


    // private static final Object monitor = new Object();

    /* ---------------Model attributes---------- */

    /* User's Roster */
    /**
     * @param leaguename
     * @param request
     * @return user's roster
     */
    @ModelAttribute("userRoster")
    public List<Roster> getRosterPlayers(@PathVariable("leaguename") String leaguename,
            HttpServletRequest request) {
        List<Roster> roster = new ArrayList<Roster>();
        if (request.getUserPrincipal() != null) {

            String username = request.getUserPrincipal().getName();
            roster = getTeamRoster(leaguename, username);

        }
        return roster;
    }

    /* ---------------Model attributes end---------- */

    /*----------starting draft---------------*/
    /**
     * Method start draft. First it loads all free players, then teams queue and
     * then all picks
     * 
     * @param leaguename
     * @return 1 or 0
     */
    @RequestMapping(value = "{leaguename}/startdraft", method = RequestMethod.POST)
    public @ResponseBody
    String startDraft(@PathVariable("leaguename") String leaguename) {
        System.err.println("Starting draft...");
        try {
            // load league
            Leagues league = new Leagues();
            league = leaguesService.getLeaguebyName(leaguename);

            // load players
            Map<Integer, Lplayers> players = new LinkedHashMap<Integer, Lplayers>();
            List<Lplayers> playerslist = new ArrayList<Lplayers>();
            playerslist = lplayersService.getAllDraftPlayers(league);
            players = listToLinkedHashMap(playerslist);
            leagueplayers.put(leaguename, players);

            // load draftqueue
            List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
            draftqueue = draftqueueService.getQueue(league);
            leaguequeue.put(leaguename, draftqueue);

            // load draftpicks and size
            List<Draftpicks> draftpicks = new ArrayList<Draftpicks>();
            draftpicks = draftpicksService.getQueue(league);
            leaguepicks.put(leaguename, draftpicks);
            leaguepickscount.put(leaguename, draftpicks.size());

            System.err.println("Draft started");
            return "1";
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return "0";
    }

    /**
     * @param players
     * @return LinkedHashMap
     */
    public static Map<Integer, Lplayers> listToLinkedHashMap(List<Lplayers> players) {
        Map<Integer, Lplayers> lmap = new LinkedHashMap<Integer, Lplayers>();
        for (int i = 0; i < players.size(); i++) {
            lmap.put(players.get(i).getLplayerID(), players.get(i));
        }
        return lmap;
    }

    /*-------------------------Managing online----------------------------------*/
    /**
     * 
     * enter draft. Checking online users getting from draftqueue, when user on
     * draftpage, method change status to online, when user closes jsp page,
     * status set to offline
     * 
     * @param leaguename
     * @param request
     * @param draftqueue
     * @return
     */
    @RequestMapping(value = "{leaguename}/enter", method = RequestMethod.POST)
    public @ResponseBody
    String enterDraft(@PathVariable("leaguename") String leaguename, HttpServletRequest request) {
        try {
            List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
            draftqueue = leaguequeue.get(leaguename);

            if (request.getUserPrincipal() != null) {
                for (int i = 0; i < draftqueue.size(); i++) {
                    if (draftqueue.get(i).getDraftqueueID().getTeam().getUuser().getUsername()
                            .equals(request.getUserPrincipal().getName())) {
                        draftqueue.get(i).setIsOnline(1);
                        draftqueueService.updateQueue(draftqueue.get(i));

                    }
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return "1";
    }

    /**
     * Leave draft
     * 
     * @param leaguename
     * @param request
     * @param draftqueue
     * @return
     */
    @RequestMapping(value = "{leaguename}/leave", method = RequestMethod.POST)
    public @ResponseBody
    String leaveDraft(@PathVariable("leaguename") String leaguename, HttpServletRequest request) {
        try {
            List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
            draftqueue = leaguequeue.get(leaguename);

            if (request.getUserPrincipal() != null) {
                for (int i = 0; i < draftqueue.size(); i++) {
                    if (draftqueue.get(i).getDraftqueueID().getTeam().getUuser().getUsername()
                            .equals(request.getUserPrincipal().getName())) {
                        draftqueue.get(i).setIsOnline(0);
                        draftqueueService.updateQueue(draftqueue.get(i));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return "1";
    }

    /*-------------------------Managing online end----------------------------------*/

    /*------------------------Getting data via Ajax-------------------------------*/
    /**
     * Method obtains list of players from model attrubite
     * 
     * @param leaguename
     * @param players
     * @return list of players
     */
    @RequestMapping(value = "{leaguename}/players", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Map<Integer, Lplayers> getPlayers(@PathVariable("leaguename") String leaguename) {
        Map<Integer, Lplayers> players = new LinkedHashMap<Integer, Lplayers>();
        players = leagueplayers.get(leaguename);
        return players;
    }

    /**
     * @param leaguename
     * @return list of teams in queue
     */
    @RequestMapping(value = "{leaguename}/queue", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Draftqueue> getQueue(@PathVariable("leaguename") String leaguename) {
        List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
        draftqueue = leaguequeue.get(leaguename);
        return draftqueue;
    }

    /**
     * @param leaguename
     * @param draftpicks
     * @return list of picks
     */
    @RequestMapping(value = "{leaguename}/picks", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Draftpicks> getPicks(@PathVariable("leaguename") String leaguename) {
        List<Draftpicks> draftpicks = new ArrayList<Draftpicks>();
        draftpicks = leaguepicks.get(leaguename);
        return draftpicks;
    }

    /**
     * @param leaguename
     * @param draftpicks
     * @return list of picks
     */
    @RequestMapping(value = "{leaguename}/lastpick", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Draftpicks getLastPick(@PathVariable("leaguename") String leaguename) {
        List<Draftpicks> draftpicks = new ArrayList<Draftpicks>();
        draftpicks = leaguepicks.get(leaguename);
        // get last
        return draftpicks.get(draftpicks.size() - 1);
    }

    /**
     * Getting last drafted player
     * 
     * @param leaguename
     * @return
     */
    @RequestMapping(value = "{leaguename}/lastplayer", method = RequestMethod.POST)
    public @ResponseBody
    int getLastPlayer(@PathVariable("leaguename") String leaguename) {
        List<Draftpicks> draftpicks = new ArrayList<Draftpicks>();
        draftpicks = leaguepicks.get(leaguename);
        int lpID = draftpicks.get(draftpicks.size() - 1).getLpplayer().getLplayerID();
        // get last
        return lpID;
    }

    /*------------------------Getting data via Ajax end-------------------------------*/

    /**
     * Method picks a player for offline teams and set timer for online users
     * 
     * @param leaguename
     * @return 1 or 0
     * @throws InterruptedException
     */
    @RequestMapping(value = "{leaguename}/nextpick", method = RequestMethod.POST)
    public @ResponseBody
    String nextPick(@PathVariable("leaguename") String leaguename) throws InterruptedException {

        List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
        draftqueue = leaguequeue.get(leaguename);

        if (draftqueue.size() != 0) {
            // get size of picks
            // int size = getDraftPicks(leaguename).size();
            Integer size = leaguepickscount.get(leaguename);

            // if player offline
            if (draftqueue.get(0).getIsOnline() != 1) {
                Thread.sleep(3000);// qait 1 sec
                // autopick
                playerToPick(leaguename, draftqueue.get(0).getDraftqueueID().getTeam().getUuser()
                        .getUsername());
            } else {
                // 30 sec for pick
                for (int i = 0; i < 30; i++) {
                    System.err.println("seconds= " + i);

                    Thread.sleep(1000);
                    // get size of picks
                    int newsize = leaguepickscount.get(leaguename);

                    // if size changed - user pick a player
                    if (newsize > size)
                        break;
                    // auto pick on last second
                    if (i == 29)
                        playerToPick(leaguename, draftqueue.get(0).getDraftqueueID().getTeam()
                                .getUuser().getUsername());
                }
            }
        }

        return "1";
    }

    /**
     * @param leaguename
     * @return draft view
     */
    @RequestMapping("{leaguename}")
    public String toDraft(@PathVariable("leaguename") String leaguename) {
        return "draft";
    }

    /**
     * @param leaguename
     * @param roster
     * @return user's roster
     */
    @RequestMapping(value = "{leaguename}/roster", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Roster> getRoster(@PathVariable("leaguename") String leaguename,
            @ModelAttribute("userRoster") ArrayList<Roster> roster) {
        return roster;
    }

    /**
     * @param leaguename
     * @param roster
     * @return
     */
    @RequestMapping(value = "{leaguename}/teamroster", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Roster> getTeamRoster(@PathVariable("leaguename") String leaguename,
            @RequestParam("username") String username) {

        List<Roster> roster = new ArrayList<Roster>();
        try {

            Users user = new Users();
            user = usersService.getUserByName(username);

            Leagues league = new Leagues();
            league = leaguesService.getLeaguebyName(leaguename);

            Teams team = new Teams();
            team = teamsService.getTeamBy(user, league);

            roster = rosterService.getTeamRoster(team);

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return roster;
    }

    /*----------------Auto pick---------------------------*/
    @RequestMapping(value = "{leaguename}/autopick", method = RequestMethod.POST)
    public @ResponseBody
    String playerToPick(@PathVariable("leaguename") String leaguename,
            @RequestParam("username") String username) throws InterruptedException {
        // get team roster
        Lplayers player = new Lplayers();
        try {

            List<Roster> roster = new ArrayList<Roster>();
            roster = getTeamRoster(leaguename, username);
            Leagues league = leaguesService.getLeaguebyName(leaguename);
            player = DraftOperations.playerToDraft(roster, league, lplayersService);

            // draft player
            draftPlayer(player.getLplayerID(), username, leaguename);

            return "1";

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return "0";
    }

    /**
     * Method adds players to roster
     * 
     * @param playerID
     * @param username
     * @param leaguename
     * @return state of operation
     * @throws InterruptedException
     */
    @RequestMapping(value = "{leaguename}/draftplayer", method = RequestMethod.GET)
    public @ResponseBody
    String draftPlayer(@RequestParam("lplayerID") int lplayerID,
            @RequestParam("username") String username, @PathVariable("leaguename") String leaguename)
            throws InterruptedException {
        try {

            Leagues league = leaguesService.getLeaguebyName(leaguename);
            Users user = usersService.getUserByName(username);
            Teams team = new Teams();
            team = teamsService.getTeamBy(user, league);

            Lplayers player = new Lplayers();
            player = lplayersService.getLplayer(lplayerID);

            Roster roster = new Roster();
            RosterID rosterID = new RosterID();
            rosterID.setLplayer(player);
            rosterID.setTeam(team);
            roster.setRosterID(rosterID);
            rosterService.saveRoster(roster);

            player.setPteam(team);
            lplayersService.updatePlayer(player);

            // reload lplayers list
            // !!!!!!!!!!!!!!DELETE PLAYER
            Map<Integer, Lplayers> players = new LinkedHashMap<Integer, Lplayers>();
            players = leagueplayers.get(leaguename);
            players.remove(lplayerID);

            List<Draftqueue> draftqueue = new ArrayList<Draftqueue>();
            draftqueue = leaguequeue.get(leaguename);

            /* from draftqueue to draft picks */
            Draftpicks draftpick = new Draftpicks();
            draftpick.setDraftqueueID(draftqueue.get(0).getDraftqueueID());
            draftpick.setLpplayer(player);
            draftpicksService.saveDraftpick(draftpick);

            // add to leaguepicks
            List<Draftpicks> draftpicks = new ArrayList<Draftpicks>();
            draftpicks = leaguepicks.get(leaguename);
            draftpicks.add(draftpick);
            leaguepicks.put(leaguename, draftpicks);

            // remove from DB
            draftqueueService.deleteQueue(draftqueue.get(0));
            // remove from leaguequeue
            draftqueue.remove(0);
            // update leaguequeue attribute
            leaguequeue.put(leaguename, draftqueue);

            Integer picks = leaguepickscount.get(leaguename);
            picks++;
            leaguepickscount.put(leaguename, picks);

            /*------------------------------*/

            LOG.log(Level.INFO, "Player " + player.getPplayer().getLastname() + " drafted by team "
                    + team.getName() + " ");

            nextPick(leaguename); // to next pick

            return "1";
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        } catch (NumberFormatException e3) {
            LOG.log(Level.SEVERE, "Exception: ", e3);
        }
        return "0";
    }

    /* ----cheching update---------- */
    @RequestMapping(value = "{leaguename}/checkupdate", method = RequestMethod.POST)
    public @ResponseBody
    int getSize(@PathVariable("leaguename") String leaguename) {
        int picks = leaguepickscount.get(leaguename);
        return picks;
    }

}
