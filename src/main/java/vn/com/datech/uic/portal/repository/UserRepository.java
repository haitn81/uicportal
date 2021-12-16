/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.datech.uic.portal.entity.User;

import java.util.List;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    /**
     * Find by username and active user.
     *
     * @param username the username
     * @param active   the active
     * @return the user
     */
    @Query("select u from User u where u.username = :username and u.active = :active and u.deleted = false ")
    User findByUsernameAndActive(@Param("username") String username, @Param("active") boolean active);

    @Query("select u from User u where u.deleted = false and u.username = lower(:username) ")
    User findByUsername(@Param("username") String username);

    /**
     * Count by username long.
     *
     * @param username the username
     * @return the long
     */
    @Query("select count(u) from User u where u.username = lower(:username) and u.deleted = false ")
    long countByUsername(@Param("username") String username);
}
