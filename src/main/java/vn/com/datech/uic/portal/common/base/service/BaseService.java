/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vn.com.datech.uic.portal.common.base.entity.BaseEntity;
import vn.com.datech.uic.portal.common.utils.CommonUtils;
import vn.com.datech.uic.portal.common.utils.MessageUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Base service.
 */
@Component
@Log4j2
public class BaseService {
    /**
     * ModelMapper.
     */
    private ModelMapper modelMapper;
    /**
     * The Entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * The Application context.
     */
    private ApplicationContext applicationContext;

    /**
     * The Message utils.
     */
    private MessageUtils messageUtils;

    /**
     * Fill base info.
     *
     * @param entity   the entity
     * @param account  the account
     * @param isUpdate the is update
     */
    public void fillBaseInfo(final BaseEntity entity,
                                   final String account, boolean isUpdate) {
        Date now = new Date();
        if (!isUpdate) {
            entity.setCreatedBy(account.toLowerCase());
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setModifiedBy(account.toLowerCase());
            entity.setId(CommonUtils.getCurrentTimeStamp());
        } else {
            entity.setModifiedBy(account.toLowerCase());
            entity.setModifiedTime(new Date());
        }
    }

    /**
     * Init query params.
     *
     * @param query  the query
     * @param params the params
     */
    public void initQueryParams(final Query query,
                                final Map<String, Object> params) {
        if (!ObjectUtils.isEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Check system admin role boolean.
     *
     * @param account the account
     * @param exclude the exclude
     * @return the boolean
     */
    public boolean checkSystemAdminRole(final String account,
                                        final String exclude) {
        return true;
    }

    /**
     * Check system admin role boolean.
     *
     * @param account the account
     * @return the boolean
     */
    public boolean checkSystemAdminRole(final String account) {
        return checkSystemAdminRole(account, null);
    }

    /**
     * Check system admin role boolean.
     *
     * @param account the account
     * @return the boolean
     */
    public boolean checkSystemAdminRoleExceptBov(final String account) {
        return checkSystemAdminRole(account);
    }

    /**
     * Init paging.
     *
     * @param query    the query
     * @param pageNo   the page no
     * @param pageSize the page size
     */
    public void initPaging(final Query query, int pageNo, int pageSize) {
        query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
    }

    /**
     * Gets user ip.
     *
     * @return the user ip
     */
    public String getUserIp() {
        WebAuthenticationDetails request = (WebAuthenticationDetails)
                SecurityContextHolder.getContext().getAuthentication()
                        .getDetails();
        return request.getRemoteAddress();
    }

    /**
     * Gets model mapper.
     *
     * @return the model mapper
     */
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    /**
     * Sets model mapper.
     *
     * @param pModelMapper the p model mapper
     */
    @Autowired
    public void setModelMapper(final ModelMapper pModelMapper) {
        modelMapper = pModelMapper;
    }

    /**
     * Gets entity manager.
     *
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets logged in username.
     *
     * @return the logged in username
     */
    public String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Gets application context.
     *
     * @return the application context
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Sets application context.
     *
     * @param pApplicationContext the p application context
     */
    @Autowired
    public void setApplicationContext(
            final ApplicationContext pApplicationContext) {
        applicationContext = pApplicationContext;
    }

    /**
     * Gets message utils.
     *
     * @return the message utils
     */
    public MessageUtils getMessageUtils() {
        return messageUtils;
    }

    /**
     * Sets message utils.
     *
     * @param messageUtils the message utils
     */
    @Autowired
    public void setMessageUtils(final MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

}
