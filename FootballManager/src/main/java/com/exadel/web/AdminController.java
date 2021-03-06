package com.exadel.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exadel.Offense;
import com.exadel.orm.Leagues;
import com.exadel.orm.Players;
import com.exadel.orm.Users;
import com.exadel.services.ILeaguesServices;
import com.exadel.services.IPlayersServices;
import com.exadel.services.IUsersServices;

/**
 * @author st02
 * 
 */
/* Test class */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUsersServices usersService;

    @Autowired
    private ILeaguesServices leaguesService;
    
    @Autowired
    private IPlayersServices playersService;

    private static final Logger LOG = Logger.getLogger(Offense.class.getName());

    private String searchname = "";

    
    /* -------------Model Attributes-------------- */
    /**
     * @return search value
     */
    @ModelAttribute("search_name")
    public String getSearchname() {

        return searchname;
    }

    /**
     * @return new user
     */
    @ModelAttribute("user")
    public Users loadEmptyModelBean() {

        return new Users();
    }

    /**
     * @return list of users
     */
    @ModelAttribute("usersList")
    public List<Users> loadAllUsers() {
        List<Users> users = new ArrayList<Users>();
  
        try {
            if (searchname.equals("") || searchname==null)// if search input
                                                              // value empty
                users = usersService.getAllUsers();
            else {
                users = usersService.searchUser(searchname);
            }
        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return users;
    }

    /**
     * @param request
     * @return list of all leagues
     */
    @ModelAttribute("allLeaguesAdmin")
    public List<Leagues> loadAllLeagues(HttpServletRequest request) {

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

    /* -------------Model Attributes End-------------- */

    /**
     * @return admin page
     */
    @RequestMapping("/")
    public String home() {
        return "redirect:/admin";
    }

    @RequestMapping("/leagues")
    public String adminLeagues() {
        return "admin_leagues";
    }

    /**
     * @return list of users view
     */
    @RequestMapping("users")
    public String adminUsers() {
        return "admin_users";
    }

    /**
     * @param name
     * @return list of matched users
     */
    @RequestMapping("/search/{search_name}")
    public String loadUsers(@PathVariable("search_name") String name) {
        searchname = name;
        return "redirect:/admin/users";
    }

    /**
     * @return all users
     */
    @RequestMapping("/search")
    public String loadeEmptyUsers() {

        searchname = null;

        return "redirect:/admin/users";
    }

    /**
     * @return all users
     */
    @RequestMapping("/search/")
    public String loadeEmptyUsers2() {

        searchname = null;

        return "redirect:/admin/users";
    }

    /**
     * Method deletes user
     * 
     * @param username
     * @return admin user's view
     */
    @RequestMapping("/delete/{user.username}")
    public String deleteUser(@PathVariable("user.username") String username) {

        Users user = usersService.getUserByName(username);
        usersService.deleteUser(user);

        return "redirect:/admin/users";
    }

    /**
     * Method edits user
     * 
     * @param username
     * @param newusername
     * @param password
     * @param group
     * @param email
     * @return users view
     */
    @RequestMapping("/edit/{user.username},{newusername},{user.password},{user.userGroup},{user.email}")
    public String editUser(@PathVariable("user.username") String username,
            @PathVariable("newusername") String newusername,
            @PathVariable("user.password") String password,
            @PathVariable("user.userGroup") String group, @PathVariable("user.email") String email) {

        try {

            Users user = usersService.getUserByName(username);

            user.setUsername(newusername);
            user.setPassword(password);
            user.setUserGroup(group);
            user.setEmail(email);

            usersService.updateUser(user);

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return "redirect:/admin/users";
    }
    
    /*----------create player-----------*/
    @InitBinder("player")
    public void dateBinder(WebDataBinder binder) {
        // The date format to parse or output your dates
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        // Create a new CustomDateEditor
        final CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        // Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
    
    @RequestMapping("createplayer")
    public String playercreate() {
        return "createplayer";
    }
    
    @ModelAttribute("player")
    public Players loadEmptyModelBean2() {
        return new Players();
    }
    
    @RequestMapping(value = "/addplayer", method = RequestMethod.POST)
    public String addplayer(@ModelAttribute("player") Players player, BindingResult result,
            RedirectAttributes attributes) {

        try {
            playersService.savePlayer(player);

        } catch (IndexOutOfBoundsException e2) {
            LOG.log(Level.SEVERE, "Exception: ", e2);
        } catch (HibernateException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }

        return "createplayer";
    }


}
