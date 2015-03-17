package com.exadel.dao;
import java.util.List;

import com.exadel.orm.Users;
/**
 * @author st02
 *
 */
public interface IUsersDao {
    /**
     * saves user in db
     * @param users
     */
    public void save(Users users);
    /**
     * delete user from db
     * @param users
     */
    public void delete(Users users);
    /**
     * updates user in db
     * @param users
     */
    public void update(Users users);
    /**
     * returns the list of all users
     * @return all users
     */
    public List<Users> getAllUsers();
    /**
     * @param name
     * @return user by name
     */
    public Users getUserbyName(String name);
    /**
     * changes user's password
     * @param user
     * @param password
     */
    public void changePassword(Users user,String password);
    /**
     * changes user's group
     * @param user
     * @param group
     */
    public void changeGroup(Users user,String group);
    /**
     * Searches all users by name
     * @param name
     * @return list of users
     */
    public List<Users> searchUser(String name);
    
}
