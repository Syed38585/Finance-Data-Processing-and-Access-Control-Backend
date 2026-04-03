package com.zorvyn.project.Security;

import com.zorvyn.project.Model.User;
import com.zorvyn.project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userRepo.findByEmailIgnoreCase(username.trim())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomerUserDetails(user);
        }
    }