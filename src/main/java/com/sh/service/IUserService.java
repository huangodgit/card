package com.sh.service;

import com.sh.domain.User;

public interface IUserService {
    User login(User user);
    int insert(User user);
}
