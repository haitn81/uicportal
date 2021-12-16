/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import vn.com.datech.uic.portal.common.base.repository.BaseRepository;
import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserSearchForm;
import vn.com.datech.uic.portal.repository.UserRepositoryCustom;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User repository custom.
 */
@Repository
public class UserRepositoryCustomImpl extends BaseRepository implements UserRepositoryCustom {
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
        TypedQuery<User> query = this.buildSearchQuery(form, User.class, false);
        super.initPaging(query, pageNo, pageSize);
        return query.getResultList();
    }

    /**
     * Count search long.
     *
     * @param form the form
     * @return the long
     */
    @Override
    public long countSearch(final UserSearchForm form) {
        return this.buildSearchQuery(form, Long.class, true).getSingleResult();
    }


    /**
     * Build search query typed query.
     *
     * @param <T>   the type parameter
     * @param form  the form
     * @param clazz the clazz
     * @param count the count
     * @return the typed query
     */
    private <T> TypedQuery<T> buildSearchQuery(final UserSearchForm form,
                                               final Class<T> clazz,
                                               boolean count) {
        String baseQuery = "select u from User u ";
        if (count) {
            baseQuery = "select count(1) from User u ";
        }
        baseQuery = baseQuery + " where 1 = 1 and u.deleted = false ";
        StringBuilder sql = new StringBuilder(baseQuery);
        Map<String, Object> params = new HashMap<>();
        if (form.getActive() != null) {
            sql.append(" and u.active = :active ");
            params.put("active", form.getActive());
        }
        if (!ObjectUtils.isEmpty(form.getEmail())) {
            sql.append(" and u.email like :email ");
            params.put("email", "%" + form.getEmail().toLowerCase() + "%");
        }
        if (!ObjectUtils.isEmpty(form.getPhone())) {
            sql.append(" and u.phone like :phone ");
            params.put("phone", "%" + form.getPhone().toLowerCase() + "%");
        }
        if (!ObjectUtils.isEmpty(form.getFullName())) {
            sql.append(" and u.fullName like :fullName ");
            params.put("fullName", "%" + form.getFullName().toLowerCase() + "%");
        }
        return super.createQuery(sql.toString(), params, clazz);
    }
}
