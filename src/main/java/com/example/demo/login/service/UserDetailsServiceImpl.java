package com.example.demo.login.service;

import com.example.demo.login.dto.User;
import com.example.demo.login.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    Logger logger;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger = LoggerFactory.getLogger(UserDetails.class);
        logger.info("UserDetails " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        logger.info("UserDetails loadUserByUsername",user);
        logger.info("UserDetails loadUserByUsername---username1",user.getUsername());
        logger.info("UserDetails loadUserByUsername---password2",user.getPassword());


        return UserDetailsImpl.build(user);
    }

}
