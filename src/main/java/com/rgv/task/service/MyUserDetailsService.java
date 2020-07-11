package com.rgv.task.service;

import com.rgv.task.config.security.UserPrinciple;
import com.rgv.task.service.repository.User;
import com.rgv.task.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Optional<User> user = repository.findById(userId);
    if (!user.isPresent()) {
      throw new UsernameNotFoundException("Invalid User Credentials");
    }

    return new UserPrinciple(user.get());

  }
}
