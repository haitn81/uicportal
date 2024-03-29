/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import vn.com.datech.uic.portal.common.utils.MessageUtils;

/**
 * The type Base helper.
 */
@Component
public class BaseHelper {
    /**
     * The Message utils.
     */
    private MessageUtils messageUtils;
    /**
     * ModelMapper.
     */
    private ModelMapper modelMapper;

    /**
     * ApplicationContext.
     */
    private ApplicationContext applicationContext;

    /**
     * Gets message utils.
     *
     * @return the message utils
     */
    public MessageUtils getMessageUtils() {
        return messageUtils;
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
     * Sets message utils.
     *
     * @param pMessageUtils the p message utils
     */
    @Autowired
    public void setMessageUtils(final MessageUtils pMessageUtils) {
        messageUtils = pMessageUtils;
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
}
