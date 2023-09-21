package com.bestrongkids.chikingauthorizationserver.services;

import com.bestrongkids.chikingauthorizationserver.entities.User;
import com.bestrongkids.chikingauthorizationserver.model.SecurityUserDetails;
import com.bestrongkids.chikingauthorizationserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public SecurityUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("User Not Found!");

        User u = userRepository.findUserByEmail(email).orElseThrow(s);
        return new SecurityUserDetails(u);
    }
}
