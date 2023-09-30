package com.example._rent_apartment.repository;

import com.example._rent_apartment.model.Security.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserApplicationRepository extends JpaRepository<UserApplicationEntity, Long> {

    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.login = :login")
    public List<UserApplicationEntity> getUserByLogin(String login);

    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.nickName = :nickName")
    public List<UserApplicationEntity> getUserByNickName(String nickName);

}
