package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onion
 * @date 2019-05-11 -09:51
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        switch (user.getStatus()){
            case 0:
                authorityList.add(new SimpleGrantedAuthority("common user"));
                break;
            case 1:
                authorityList.add(new SimpleGrantedAuthority("student"));
                break;
            case 2:
                authorityList.add(new SimpleGrantedAuthority("graduate"));
                break;
                default:
                    authorityList.add(new SimpleGrantedAuthority("unauthorized"));
                    break;
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorityList);
    }
}
