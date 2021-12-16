/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service;

import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserCreateForm;
import vn.com.datech.uic.portal.form.user.UserSearchForm;
import vn.com.datech.uic.portal.form.user.UserUpdateForm;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Get user.
     *
     * @param username the username
     * @return the user
     */
    User get(String username);

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    User get(long id);

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    User save(User user);

    /**
     * Create user.
     *
     * @param form the form
     * @return the user
     */
    User create(UserCreateForm form);

    /**
     * Update user.
     *
     * @param form the form
     * @return the user
     */
    User update(UserUpdateForm form);

    /**
     * Search list.
     *
     * @param form     the form
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    List<User> search(UserSearchForm form, int pageNo, int pageSize);

    /**
     * Count search long.
     *
     * @param form the form
     * @return the long
     */
    long countSearch(UserSearchForm form);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);
}
