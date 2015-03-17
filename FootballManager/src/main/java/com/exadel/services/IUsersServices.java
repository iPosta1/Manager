package com.exadel.services;

import java.util.List;

import com.exadel.orm.Users;

/**
 * @author st02
 *
 */
public interface IUsersServices {

    /**
     * saves user in DB
     * @param users
     */
    public void saveUser(Users users);
    /**
     * deletes user from DB
     * @param users
     */
    public void deleteUser(Users users);
    /**
     * updates user in DB
     * @param users
     */
    public void updateUser(Users users);
    /**
     * @return all users in db
     */
    public List<Users> getAllUsers();
    /**
     * @param name
     * @return user by name
     */
    public Users getUserByName(String name);
    /**
     * changes user's password
     * @param user
     * @param password
     */
    public void changePassword(Users user, String password);
    /**
     * changes user's group
     * @param user
     * @param group
     */
    public void changeGroup(Users user, String group);
    /**
     * @param name
     * @return
     */
    public List<Users> searchUser(String name);

}
