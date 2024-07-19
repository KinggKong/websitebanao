package com.huy.ecommercebe.service;

import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.User;

public interface IUserSerivice {
    User findUserById(Long id) throws UserException;

    User findUserProfileByJwt(String jwt) throws  UserException;

    User findUserByEmail(String email) throws UserException;

    User insertUser(User user) throws UserException;

    User updateUser(User user) throws UserException;

    void deleteUser(Long id) throws UserException;
}
