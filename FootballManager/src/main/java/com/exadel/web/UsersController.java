package com.exadel.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exadel.Offense;
import com.exadel.orm.Leagues;
import com.exadel.orm.Users;
import com.exadel.orm.Weeks;
import com.exadel.services.ILeaguesServices;
import com.exadel.services.ITeamsServices;
import com.exadel.services.IUsersServices;
import com.exadel.services.IWeeksServices;

/**
 * @author st02
 * 
 */
@Controller
public class UsersController {

    @Autowired
    private IUsersServices usersService;

    @Autowired
    private ILeaguesServices leaguesService;

    @Autowired
    private ITeamsServices teamsService;

    @Autowired
    private IWeeksServices weeksService;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

 

    // for logging
    private static final Logger LOG = Logger.getLogger(Offense.class.getName());

    /**
     * convert input string to date
     * 
     * @param binder
     */
    @InitBinder("leagues")
    public void dateBinder(WebDataBinder binder) {
        // The date format to parse or output your dates
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.US);
        // Create a new CustomDateEditor
        final CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        // Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /*-------------Model attributes---------- */

    /**
     * @return new user
     */
    @ModelAttribute("user")
    public Users loadEmptyModelBean() {
        return new Users();
    }

    /**
     * @return new league
     */
    @ModelAttribute("league")
    public Leagues loadEmptyLeagueBean() {
        return new Leagues();
    }

    /**
     * @param request
     * @return all user's leagues
     */
    @ModelAttribute("userNewLeagues")
    public List<Leagues> loadUsersLeagues(final HttpServletRequest request) {

        List<Leagues> leagues = new ArrayList<Leagues>();
        try {
            if (request.getUserPrincipal() != null) {
                final String username = request.getUserPrincipal().getName();
                leagues = leaguesService.getUsersLeagues(username);
            }
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return leagues;
    }

    /**
     * Get all leagues
     * 
     * @param request
     * @return
     */
    @ModelAttribute("allLeagues")
    public List<Leagues> loadAllLeagues(final HttpServletRequest request) {

        List<Leagues> leagues = new ArrayList<Leagues>();
        try {
            leagues = leaguesService.getAllLeagues();
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return leagues;
    }

    /**
     * @return total users count
     */
    @ModelAttribute(value = "usersCount")
    public int loadUsersCount() {

        List<Users> users = new ArrayList<Users>();
        try {
            users = usersService.getAllUsers();

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return users.size();
    }

    // this method works incorrectly !! it returns only count of logged users,
    // not online
    /**
     * @return total online count
     */
    @ModelAttribute("onlineCount")
    public int loadOnlineCount() {
      //  final List<Object> principals = sessionRegistry.getAllPrincipals();
        return SessionCounterListener.getTotalActiveSession();
    }
    
    /*-------------Model attributes end---------- */

    /**
     * @return index view
     */
    @RequestMapping("/")
    public String home(RedirectAttributes attributes) {
        return "redirect:/index";
    }

    /**
     * @return admin's view
     */
    @RequestMapping("/admin")
    public String administration() {
        return "admin";
    }

    /**
     * @return leagues view
     */
    @RequestMapping("/leagues")
    public String leagues(RedirectAttributes attributes) {
        return "leagues";
    }

    // test view, should be deleted later
    @RequestMapping("/leagues2")
    public String leagues2(RedirectAttributes attributes) {
        return "leagues2";
    }

    /**
     * @return index view
     */
    @RequestMapping("/index")
    public String index(RedirectAttributes attributes) {
        return "index";
    }

    /**
     * Method adds new user in database
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("users") Users user, BindingResult result,
            RedirectAttributes attributes) {
        // ROLE_USER - default group for new users
        user.setUserGroup("ROLE_USER");
        try {
            usersService.saveUser(user);

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
            return "redirect:/index#register";
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
            return "redirect:/index#register";
        }

        return "redirect:/index";
    }

    /**
     * Method adds new league, then generates league's schedule
     * 
     * @param league
     * @param result
     * @param attributes
     * @return league's view
     */
    @RequestMapping(value = "/addLeague", method = RequestMethod.POST)
    public String addLeague(@ModelAttribute("leagues") Leagues league, BindingResult result,
            RedirectAttributes attributes) {
        try {
            league.setMaxPlayers(32);
            league.setStarted("n"); // new league

            /* take only date from startdate */
            final DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            final String startdateStr = df.format(league.getStartDate());

            /* take only time from primetime */
            final DateFormat df2 = new SimpleDateFormat("hh:mm", Locale.US);
            final String primetimeStr = df2.format(league.getPrimetime());

            /* combine date and time */
            final DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.US);
            Date date = df3.parse(startdateStr + " " + primetimeStr);

            leaguesService.saveLeague(league);
            /* create league schedule. Each league has 21 weeks */
            for (int i = 0; i < 21; i++) {
                Weeks week = new Weeks();
                week.setLeague(league);

                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1); // add one day
                date = calendar.getTime();

                week.setGameDate(date); // set game date

                if (i == 0) { // first event is a draft
                    week.setWeekName("draft");
                }
                if (i > 0 && i < 17) { // game weeks
                    week.setWeekName("week " + i);
                }
                if (i == 17) { // wild card event
                    week.setWeekName("wild card");
                }
                if (i == 18) { // playoff event
                    week.setWeekName("playoff");
                }
                if (i == 19) { // final game in the conference
                    week.setWeekName("conferense game");
                }
                if (i == 20) { // final game in the league
                    week.setWeekName("super bowl");
                }
                weeksService.saveWeek(week);
            }
            LOG.log(Level.INFO, "league "+league.getLeagueName()+" created ");
        } catch (IndexOutOfBoundsException e1) {
            LOG.log(Level.SEVERE, "Exception: ", e1);
        } catch (HibernateException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (ParseException e3) {
            LOG.log(Level.SEVERE, "Exception: ", e3);
        }
        return "redirect:/leagues";
    }

    /**
     * @return index view
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(RedirectAttributes attributes) {
        return "redirect:/";
    }

    /**
     * if sing in failed
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/signin-failure", method = RequestMethod.GET)
    public String signinFailure(RedirectAttributes attributes) {
        return "redirect:/index#login";
    }

    /**
     * Checks if there is the same user in database
     * 
     * @param usrname
     * @param session
     * @return
     */
    @RequestMapping(value = "/checkuser", method = RequestMethod.GET)
    public @ResponseBody
    String userCheck(final @RequestParam("username") String usrname, HttpSession session) {

        try {
            usersService.getUserByName(usrname);
            return "1";
        } catch (IndexOutOfBoundsException e1) {
            LOG.log(Level.WARNING, "no such user ");
        } catch (HibernateException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        }
        return "0";
    }

    /**
     * Checks if there is the league with the same name
     * 
     * @param name
     * @param session
     * @return 1 - if league with such name exists
     */
    @RequestMapping(value = "/checkleague", method = RequestMethod.GET)
    public @ResponseBody
    String leagueCheck(final @RequestParam("leaguename") String name, HttpSession session) {
        try {
            leaguesService.getLeaguebyName(name);
            return "1";
        } catch (IndexOutOfBoundsException e1) {
            LOG.log(Level.WARNING, "no such league ");
        } catch (HibernateException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        }
        return "0";
    }

    /**
     * Checks team's name
     * 
     * @param leaguename
     * @param teamname
     * @param session
     * @return 0 - if no team, 1 - if team exists
     */
    @RequestMapping(value = "/checkteam", method = RequestMethod.GET)
    public @ResponseBody
    String teamCheck(final @RequestParam("leaguename") String leaguename,
            final @RequestParam("teamname") String teamname, HttpSession session) {
        try {
            Leagues league = new Leagues();
            league = leaguesService.getLeaguebyName(leaguename);
            teamsService.getTeamByLeague(teamname, league);

            return "1";
        } catch (IndexOutOfBoundsException e1) {
            LOG.log(Level.WARNING, "no such team ");
        } catch (HibernateException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        }
        return "0";
    }

    /**
     * @param request
     * @return principal's username
     */
    @RequestMapping(value = "username", method = RequestMethod.POST)
    public @ResponseBody
    String getUsername(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return request.getUserPrincipal().getName();
        }
        return null;
    }
}