package com.sh.service.impl;

import com.sh.dao.impl.UserDao;
import com.sh.domain.User;
import com.sh.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
@Getter@Setter
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
