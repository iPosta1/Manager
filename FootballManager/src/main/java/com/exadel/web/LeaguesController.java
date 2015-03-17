package com.exadel.web;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exadel.IllegalCountException;
import com.exadel.Offense;
import com.exadel.operations.LeagueOperations;
import com.exadel.orm.Games;
import com.exadel.orm.Leagues;
import com.exadel.orm.Standings;
import com.exadel.orm.StandingsID;
import com.exadel.orm.Teams;
import com.exadel.orm.Users;
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

/**
 * @author st02
 * 
 */
@Controller
@RequestMapping("/leagues")
public class LeaguesController {

	@Autowired
	private IUsersServices usersService;

	@Autowired
	private ILeaguesServices leaguesService;

	@Autowired
	private ITeamsServices teamsService;

	@Autowired
	private IStandingsService standingsService;

	@Autowired
	private IWeeksServices weeksService;

	@Autowired
	private IGamesServices gamesService;

	@Autowired
	private IPlayersServices playersService;

	@Autowired
	private ILplayersServices lplayersService;

	@Autowired
	private IDraftqueueServices draftqueueService;
	
	// for logging
	private static final Logger LOG = Logger.getLogger(Offense.class.getName());

	/*-------------Model Attributes---------------*/
	/**
	 * @param leaguename
	 * @return list of standings
	 */
	@ModelAttribute("standings")
	public List<Standings> loadStandings(
			final @PathVariable("leaguename") String leaguename) {
		List<Standings> standings = new ArrayList<Standings>();
		try {
			Leagues league = new Leagues();
			league = leaguesService.getLeaguebyName(leaguename);
			standings = standingsService.getStandingsByLeague(league);

		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}
		return standings;
	}

	/**
	 * @param leaguename
	 * @return list of games
	 */
	@ModelAttribute("games")
	public List<Games> loadGames(
			final @PathVariable("leaguename") String leaguename) {
		List<Weeks> weeks = new ArrayList<Weeks>();
		List<Games> games = new ArrayList<Games>();
		try {
			Leagues league = new Leagues();
			league = leaguesService.getLeaguebyName(leaguename);

			// get all weeks in a league
			weeks = weeksService.getWeeksByLeague(league);

			// get games from each week
			for (int i = 0; i < weeks.size(); i++) {
				List<Games> gamesTemp = new ArrayList<Games>();
				gamesTemp = gamesService.getGamesByWeek(weeks.get(i));
				games.addAll(gamesTemp);
			}

		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}
		return games;
	}

	/**
	 * @param leaguename
	 * @return league by name
	 */
	@ModelAttribute("league")
	public Leagues loadleague(
			final @PathVariable("leaguename") String leaguename) {
		Leagues league = new Leagues();
		try {
			league = leaguesService.getLeaguebyName(leaguename);
		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}
		return league;
	}

	/**
	 * get all teams in a league
	 * 
	 * @param leaguename
	 * @return teams
	 */
	@ModelAttribute("leagueTeams")
	public List<Teams> loadleagueTeams(
			final @PathVariable("leaguename") String leaguename) {
		List<Teams> teams = new ArrayList<Teams>();
		try {
			Leagues league = leaguesService.getLeaguebyName(leaguename);
			teams = league.getTeams();
		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}
		return teams;
	}

	/**
	 * @return new user
	 */
	@ModelAttribute("user")
	public Users createNewUser() {
		return new Users();
	}

	// new team
	/**
	 * @return new team
	 */
	@ModelAttribute("team")
	public Teams createNewTeam() {
		return new Teams();
	}

	/**
	 * Method checks, if there is a user's team in the league
	 * 
	 * @param leaguename
	 * @param request
	 * @return true if exists
	 */
	@ModelAttribute("flag")
	public boolean checkUserTeam(
			final @PathVariable("leaguename") String leaguename,
			HttpServletRequest request) {
		List<Teams> teams = new ArrayList<Teams>();

		try {
			Leagues league = leaguesService.getLeaguebyName(leaguename);
			teams = league.getTeams();
			if (request.getUserPrincipal() != null) {
				for (int i = 0; i < teams.size(); i++) {
					if (teams.get(i).getUuser().getUsername()
							.equals(request.getUserPrincipal().getName()))
						return true;
				}
			}
		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}

		return false;
	}

	/*-------------Model Attributes end---------------*/

	/**
	 * @return team's view
	 */
	@RequestMapping("{leaguename}/teams")
	public String loadLeaguesTeams() {
		return "teams";
	}

	/**
	 * @return standing's view
	 */
	@RequestMapping("{leaguename}/standings")
	public String loadLeaguesStandings(RedirectAttributes attributes) {
		return "standings";
	}

	/**
	 * @return view for creation team
	 */
	@RequestMapping("/{leaguename}/teams/create")
	public String createTeam() {
		return "create_team";
	}

	/**
	 * Method for principal for leaving a league
	 * 
	 * @param request
	 * @param leaguename
	 * @param attributes
	 * @return leagues view
	 */
	@RequestMapping(value = "/{leaguename}/teams/leave")
	public String leaveLeague(HttpServletRequest request,
			@PathVariable("leaguename") String leaguename,
			RedirectAttributes attributes) {

		try {
			Leagues league = leaguesService.getLeaguebyName(leaguename);
			// if league started, you can't leave a league
			if (league.getStarted().equals("y"))
				throw new IllegalCountException("League already started");
			Teams team = new Teams();
			Users user = new Users();
			// get principal's user
			if (request.getUserPrincipal() != null) {
				user = usersService.getUserByName(request.getUserPrincipal()
						.getName());
				// get principal's team
				team = teamsService.getTeamBy(user, league);
				// delete principal's team
				teamsService.deleteTeam(team);
				LOG.log(Level.INFO, "User " + user.getUsername()
						+ " left league " + league.getLeagueName() + " ");
			}
		} catch (IllegalCountException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		} catch (IndexOutOfBoundsException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		}

		return "redirect:/leagues";
	}

	/**
	 * This method starts a league
	 * 
	 * @see com.exadel.operations.LeagueOperations#startLeague
	 * @param leaguename
	 * @return if success - redirect to league's standings, else - to league's
	 *         teams 
	 */
	@RequestMapping("/{leaguename}/teams/start")
	public String startLeague(@PathVariable("leaguename") String leaguename,
			RedirectAttributes attributes) {

		// handle services to LeagueOperations class
		if (LeagueOperations.startLeague(leaguename, leaguesService,
				teamsService, standingsService, weeksService, gamesService,
				usersService, lplayersService, playersService,
				draftqueueService)) {

			LOG.log(Level.INFO, " League " + leaguename + " started");

			return "redirect:/leagues/{leaguename}/standings";
		} else {
			LOG.log(Level.INFO, " Starting league " + leaguename + " failed");
			return "redirect:/leagues/{leaguename}/teams";
		}

	}

	/**
	 * Method creates team in a league, and then creates standing for this team
	 * in standings table
	 * 
	 * @param team
	 * @param leaguename
	 * @param result
	 * @param request
	 * @return redirect to list of teams
	 */
	
	@RequestMapping(value = "/{leaguename}/teams/add", method = RequestMethod.POST)
	public String addTeam(@ModelAttribute("teams") Teams team,
			@PathVariable("leaguename") String leaguename,
			BindingResult result, HttpServletRequest request,
			RedirectAttributes attributes) {

		try {
			String username = request.getUserPrincipal().getName();
			Users user = new Users();
			user = usersService.getUserByName(username);
			team.setUser(user);
			Leagues league = new Leagues();
			league = leaguesService.getLeaguebyName(leaguename);	
			team.setLleague(league);
			if (league.getTeams().size() > 31)
				throw new IllegalCountException("max size reached");
			
			/*-----get default image---------*/
			
			 byte[] image = null;
	            try{
	            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	            InputStream is = classloader.getResourceAsStream("nologo.jpg");    
	            image = IOUtils.toByteArray(is);
	            
	            } catch (IOException e) {
	                LOG.log(Level.SEVERE, "Exception: ", e);
	            }
			/*--------------------------------*/
	            
	        team.setTeamlogo(image);
	            
			StandingsID sid = new StandingsID(); // create new primary key
			/* key consists of 2 FK: team and league */
			sid.setTeam(team);
			sid.setLeague(league);

			Standings standing = new Standings(); // create row in standings
													// table
			standing.setTeamNumber(0);
			standing.setSID(sid); // set key

			teamsService.saveTeam(team); // save team
			standingsService.saveStanding(standing); // save team in table
														// statement

			LOG.log(Level.INFO, "User " + user.getUsername()
					+ " created team in the league " + leaguename + " ");

		} catch (HibernateException e) {
			LOG.log(Level.SEVERE, "Exception: ", e);
		} catch (IllegalCountException e2) {
			LOG.log(Level.SEVERE, "Exception: ", e2);
		} catch (IndexOutOfBoundsException e3) {
			LOG.log(Level.SEVERE, "Exception: ", e3);
		}
		return "redirect:/leagues/{leaguename}/teams";
	}

	/**
	 * @return draft's view
	 */
	@RequestMapping("/{leaguename}/draft")
	public String toDraft() {
		return "redirect:/draft/{leaguename}";
	}

}
