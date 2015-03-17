package com.exadel.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exadel.orm.Users;

/**
 * @author st02
 *
 */
public class UsersDao extends HibernateDaoSupport implements IUsersDao {

    
     /**
     * 
     * @see com.exadel.dao.IUsersDao#save(com.exadel.orm.Users)
     */
    public void save(Users users) {
        getHibernateTemplate().save(users);
    }

    /**
     * @see com.exadel.dao.IUsersDao#delete(com.exadel.orm.Users)
     */
    public void delete(Users users) {
        getHibernateTemplate().delete(users);
    }

    /**
     * @see com.exadel.dao.IUsersDao#update(com.exadel.orm.Users)
     */
    public void update(Users users) {
        getHibernateTemplate().update(users);
    }

    /**
     * @see com.exadel.dao.IUsersDao#getAllUsers()
     */
    @SuppressWarnings("unchecked")
    public List<Users> getAllUsers() {
        List<Users> users = getHibernateTemplate().find("from Users");
        return users;
    }

    /**
     * @see com.exadel.dao.IUsersDao#getUserbyName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Users getUserbyName(String name) {
        List<Users> usrlist = getHibernateTemplate().find(
                "from Users u where u.username = '" + name + "'");
        return usrlist.get(0);
    }

    /**
     * @see com.exadel.dao.IUsersDao#changePassword(com.exadel.orm.Users, java.lang.String)
     */
    public void changePassword(Users user, String password) {
        user.setPassword(password);
        getHibernateTemplate().update(user);

    }

    /**
     * @see com.exadel.dao.IUsersDao#changeGroup(com.exadel.orm.Users, java.lang.String)
     */
    public void changeGroup(Users user, String group) {
        user.setUserGroup(group);
        getHibernateTemplate().update(user);
    }

    /**
     * @see com.exadel.dao.IUsersDao#searchUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Users> searchUser(String name) {
        List<Users> usrlist = getHibernateTemplate().find(
                "from Users u where u.username LIKE '%" + name + "%'");
        return usrlist;

    }
}
