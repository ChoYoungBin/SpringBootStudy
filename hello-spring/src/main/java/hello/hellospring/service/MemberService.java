package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memberRepository


    public MemberService(MemoryMemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){
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
        return memberRepository.findByAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
