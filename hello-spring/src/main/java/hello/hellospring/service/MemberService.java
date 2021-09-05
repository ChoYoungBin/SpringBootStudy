package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     * @param member
     * @return
     */
    
    @Transactional
    public Long join(Member member) throws SQLException {
        validateDuplicatedMember(member);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new RuntimeException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
