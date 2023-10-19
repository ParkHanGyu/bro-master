package com.brogrammers.brogrammers.domain.repository;

import com.brogrammers.brogrammers.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository // 데이터 베이스 관련 어노테이션
public class MemberRepository {

    @PersistenceContext // == autowired
    private EntityManager em; // 영속성 / 상자에다가 멤버를 담아둠. 아직 쿼리문 안 나간 상태 .

    public void save(Member member) {
        em.persist(member);
    }// 회원 저장 메서드, 아직까진 데이터베이스에 저장이 안 됨.

    public Member findOne(String memberEmail) {
        Member member = em.find(Member.class,memberEmail);
        return member;
    }
    public Member findById(Long id) {
        Member member = em.find(Member.class,id);
        return member;
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m ",Member.class)
                .getResultList();
    }

    public Optional<Member> findByEmail(String memberEmail){
        Optional<Member> getMember = findAll().stream().filter(m->m.getEmail().equals(memberEmail))
                .findFirst();
        return getMember;
    }
}
