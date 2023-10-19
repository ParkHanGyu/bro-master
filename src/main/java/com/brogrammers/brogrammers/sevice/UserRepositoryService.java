package com.brogrammers.brogrammers.sevice;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService {
    @Autowired
    MemberRepository userRepository;

    public void save(Member member){
        userRepository.save(member);
    }
}
