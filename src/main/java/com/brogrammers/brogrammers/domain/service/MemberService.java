package com.brogrammers.brogrammers.domain.service;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) // @Transactional 이 때 DB에 저장을 해줌.
@RequiredArgsConstructor // 생성자 만들주는건데 , final이 붙은 필드를 매개변수로 받는 생성자를 생성
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validataDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Member findById(Long id){
        return memberRepository.findById(id);
    }
    // 회원 중복 확인 메서드
    private void validataDuplicateMember(Member member) {
        Optional<Member> member1 = memberRepository.findByEmail(member.getEmail());
        if(!member1.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
