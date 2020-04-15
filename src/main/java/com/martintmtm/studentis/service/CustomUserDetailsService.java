/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.service;

import com.martintmtm.studentis.entity.User;
import com.martintmtm.studentis.model.MyUserDetails;
import com.martintmtm.studentis.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username with name %s does not exist.", username));
        }
        return new MyUserDetails(user.get());
    }
    
    
}
