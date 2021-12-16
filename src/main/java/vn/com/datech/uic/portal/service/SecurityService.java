/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.com.datech.uic.portal.common.utils.MessageUtils;
import vn.com.datech.uic.portal.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Security service.
 */
@Service
public class SecurityService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        List<User> userRoles = null;
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        return new org.springframework.security.core.userdetails.User(
                username, "",
                mapRolesToAuthorities(roles));
    }

    /**
     * map roles to authorities.
     *
     * @param roles
     *            List of roles
     * @return Collection of GrantedAuthority
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
            final List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
