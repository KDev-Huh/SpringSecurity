package com.example.springsecurity.service;

import com.example.springsecurity.dto.JoinDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    final private UserRepository userRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {

        //db에 동일한 username이 있는지 확인이 필요하다
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if(isUser) {
            return;
        }

        User user = new User();

        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
