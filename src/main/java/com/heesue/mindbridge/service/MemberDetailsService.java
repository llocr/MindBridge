package com.heesue.mindbridge.service;

import com.heesue.mindbridge.entity.Member;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }


//    private UserDetails toUserDetails(Member member) {
//        return User.builder()
//                .username(member.getId())
//                .password(member.getPassword())
//                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
//                .build();
//    }
}


