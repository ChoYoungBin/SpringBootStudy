package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIT {

    @Autowired
    private MemberService mockMemberService;

    @Test
    void test_join_returnsMemberId() throws SQLException {
        //given
        Member member = new Member();
        member.setName("name1");

        //when
        Long join = mockMemberService.join(member);
        Member member1 = mockMemberService.findOne(join).get();


        //then
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    void test_join_returnsErrorWhenDuplication() throws SQLException {
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
}
