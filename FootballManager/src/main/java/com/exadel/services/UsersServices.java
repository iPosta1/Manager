package com.exadel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.dao.IUsersDao;
import com.exadel.orm.Users;

/**
 * @author st02
 *
 */
@SuppressWarnings("deprecation")
@Service("UsersServices")
@Transactional(readOnly = false)
public class UsersServices implements IUsersServices,UserDetailsService  {

    @Autowired
    private IUsersDao usersDao;

    public void setDao(IUsersDao dao) {
        this.usersDao = dao;
    }
    
  

    /**
     * @see com.exadel.services.IUsersServices#saveUser(com.exadel.orm.Users)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUser(Users user) {
        
        usersDao.save(user);
       
    }

    /**
     * @see com.exadel.services.IUsersServices#deleteUser(com.exadel.orm.Users)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUser(Users user) {
        usersDao.delete(user);
    }

    /**
     * @see com.exadel.services.IUsersServices#updateUser(com.exadel.orm.Users)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUser(Users user) {
        usersDao.update(user);
    }

    /**
     * @see com.exadel.services.IUsersServices#getAllUsers()
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }

    /**
     * @see com.exadel.services.IUsersServices#getUserByName(java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Users getUserByName(String name) {
        return usersDao.getUserbyName(name);
    }

    /**
     * @see com.exadel.services.IUsersServices#changePassword(com.exadel.orm.Users, java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void changePassword(Users user, String password) {
        usersDao.changePassword(user, password);
    }

    /**
     * @see com.exadel.services.IUsersServices#changeGroup(com.exadel.orm.Users, java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void changeGroup(Users user, String group) {
        usersDao.changeGroup(user, group);
    }
    
    /**
     * @see com.exadel.services.IUsersServices#searchUser(java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Users> searchUser(String name) {
        return usersDao.searchUser(name);
    }
    
    /*------------------ security------------------------*/
    
    public UserDetails loadUserByUsername(final String username) 
            throws UsernameNotFoundException {
     Users user = usersDao.getUserbyName(username);
     
     GrantedAuthority authorities = buildUserAuthority(user.getUserGroup());

     return buildUserForAuthentication(user, authorities);


 }
 
    // Converts Users user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Users user, 
       GrantedAuthority authority) {
        
        List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        
        User newuser = new User(user.getUsername(),user.getPassword(),true,true,true,true,authorities);
        
        
        return newuser;
    }
 
    private GrantedAuthority buildUserAuthority(String userRole) {
 
        GrantedAuthority setAuths = new GrantedAuthorityImpl(userRole);
 
        return setAuths;
    }
    
 

}
