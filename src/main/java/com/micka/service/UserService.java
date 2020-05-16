package com.micka.service;

import com.micka.dto.User;

public interface UserService {

    User findById(Integer id);

    Integer save(User user);

    Integer update(User user);

    void delete(Integer id);

}
