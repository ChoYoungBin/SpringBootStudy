package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    private MemoryMemberRepository mockMemberRepository;
    private MemberService mockMemberService;

    @BeforeEach
    public void beforeEach(){
        mockMemberRepository = new MemoryMemberRepository();
        //DI (dependency Injection)
        mockMemberService = new MemberService(mockMemberRepository);
    }

    @AfterEach
    void cleanUp(){
        mockMemberRepository.clearStore();
    }

    @Test
    void test_join_returnsMemberId() {
        //given
        Member member = new Member();
        member.setName("name1");

        //when
        Long join = mockMemberService.join(member);

        //then
        assertThat(join).isEqualTo(1L);
    }

    @Test
    void test_join_returnsErrorWhenDuplication() {
        //given
        Member member1 = new Member();
        member1.setName("name1");
        Member member2 = new Member();
        member2.setName("name1");

        //when
        mockMemberService.join(member1);

        //then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> mockMemberService.join(member2));
        assertThat(runtimeException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void test_findOne() {
    }
}