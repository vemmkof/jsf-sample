package com.ipn.escom.dao;

import java.util.List;
import com.ipn.escom.entity.UserEntity;


public interface UserDAO {

  UserEntity saveUser(UserEntity user);

  UserEntity updateUser(UserEntity user);

  UserEntity findUser(UserEntity user);

  List<UserEntity> findAllUsers();

  List<UserEntity> findUserByUsernameAndPassword(UserEntity user);

  List<UserEntity> findUserByUsername(UserEntity user);

  boolean deleteUser(UserEntity user);

}
