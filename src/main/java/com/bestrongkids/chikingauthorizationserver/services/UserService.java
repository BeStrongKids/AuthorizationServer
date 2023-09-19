package com.bestrongkids.chikingauthorizationserver.services;

import com.bestrongkids.chikingauthorizationserver.dto.UserDto;
import com.bestrongkids.chikingauthorizationserver.entities.User;
import com.bestrongkids.chikingauthorizationserver.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public String userRegister(@NonNull UserDto userDto){
        User user = User.builder()
                .name(userDto.name())
                .email(userDto.email())
                .password(userDto.password())
                .nonExpired(true)
                .nonBlocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userRepository.save(user);

        return "save";
    }
}
