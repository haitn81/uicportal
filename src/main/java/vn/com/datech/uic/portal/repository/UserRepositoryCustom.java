/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.repository;

import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserSearchForm;

import java.util.List;

/**
 * The interface User repository custom.
 */
public interface UserRepositoryCustom {
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
}
