package com.example.springsecurity.service;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 로그인시 spring security가 그 요청을 가로채는데 이때 db에 있는 값과 비교하기 위해서는 우리가 직접 그 메서드를 구현해주어야 한다.
// 따라서 UserDetailsService를 구현한다.
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    final private UserRepository userRepository;

    // 스프링 시큐리티가 username에 사용자의 아이디를 넣어준다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userData = userRepository.findByUsername(username);
        if(userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
