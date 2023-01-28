package com.henry.facetcher.security.jwt;

import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Slf4j
@Service
@AllArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("UserDetailsService: loadUserByUsername() called");
        UserDto userDto = userService.findUserByEmail(email);
        if (userDto.getMarkedAsDeleted()) throw new RuntimeException("Account deactivated");
        return new org.springframework.security.core.userdetails.User(userDto.getEmail(),
                userDto.getPassword(), getAuthority(userDto.getUserRoles()));
    }

    private Set<SimpleGrantedAuthority> getAuthority(List<UserRoleDto> userRoles) {
        log.info("UserDetailsService: getAuthority() called");
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(userRoleDto -> {
            if (!userRoleDto.getMarkedAsDeleted())
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleDto.getRole().getName()));
        });
        return authorities;
    }
}