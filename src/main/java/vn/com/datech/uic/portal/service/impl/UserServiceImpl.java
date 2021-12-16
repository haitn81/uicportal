/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service.impl;

import org.springframework.stereotype.Service;
import vn.com.datech.uic.portal.common.base.service.BaseService;
import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserCreateForm;
import vn.com.datech.uic.portal.form.user.UserSearchForm;
import vn.com.datech.uic.portal.form.user.UserUpdateForm;
import vn.com.datech.uic.portal.repository.UserRepository;
import vn.com.datech.uic.portal.service.UserService;

import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    /**
     * The User repository.
     */
    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param pUserRepository the p user repository
     */
    public UserServiceImpl(final UserRepository pUserRepository) {
        this.userRepository = pUserRepository;
    }

    /**
     * Get user.
     *
     * @param username the username
     * @return the user
     */
    @Override
    public User get(final String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    @Override
    public User get(final long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User save(final User user) {
        boolean isUpdate = user.getId() != null;
        user.setUsername(user.getUsername().toLowerCase());
        super.fillBaseInfo(user, super.getLoggedInUsername(), isUpdate);
        return userRepository.save(user);
    }

    /**
     * Create user.
     *
     * @param form the form
     * @return the user
     */
    @Override
    public User create(final UserCreateForm form) {
        User user = super.getModelMapper().map(form, User.class);
        user.setActive(true);
        user.setDeleted(false);
        return this.save(user);
    }

    /**
     * Update user.
     *
     * @param form the form
     * @return the user
     */
    @Override
    public User update(final UserUpdateForm form) {
        User user = this.get(form.getId());
        if (user != null) {
            super.getModelMapper().map(form, user);
            return this.save(user);
        } else {
            return null;
        }
    }

    /**
     * Search list.
     *
     * @param form     the form
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    @Override
    public List<User> search(final UserSearchForm form, final int pageNo, final int pageSize) {
        return userRepository.search(form, pageNo, pageSize);
    }

    /**
     * Count search long.
     *
     * @param form the form
     * @return the long
     */
    @Override
    public long countSearch(final UserSearchForm form) {
        return userRepository.countSearch(form);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(final long id) {
        User user = this.get(id);
        if (user != null) {
            user.setDeleted(true);
            this.save(user);
        }
    }
}
