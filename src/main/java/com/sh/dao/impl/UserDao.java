package com.sh.dao.impl;

import com.sh.dao.DaoHibernate;
import com.sh.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends DaoHibernate<User> {
    public User login(User user){
        String hql = "from User where userName=? and userPassword=?";
        String param[]={user.getUserName(),user.getUserPassword()};
        User user1 = this.findOne(hql,param);
        return user1;
    }
    public User findByName(User user){
        String hql="from User where userName=?";
        String param[] = {user.getUserName()};
        User user1 = this.findOne(hql,param);
        return user1;
    }
    public int updatePassword(User user, String newPassword){
        User user1 = this.login(user);
        user1.setUserPassword(newPassword);
        return this.update(user1);
    }
}
