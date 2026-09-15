package com.tatkal.backend_app.security;

import com.tatkal.backend_app.entities.User;
import com.tatkal.backend_app.exceptions.ResourseNotFoundException;
import com.tatkal.backend_app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourseNotFoundException("Invalid email or password !!"));

        return user;
    }
}
