package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloSpringApplicationTests {

    MemoryMemberRepository mockmemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
    	mockmemberRepository.clearStore();
	}

    @Test
    void test_save_returnsMember() {
        Member member = new Member();
        member.setName("name");


        Member response = mockmemberRepository.save(member);


        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("name");
    }

    @Test
    void test_findByName_returnsSpecificMember(){
        Member member1 = new Member();
        member1.setName("name1");
        mockmemberRepository.save(member1);
        Member member2 = new Member();
        member2.setName("name2");
        mockmemberRepository.save(member2);


        Member response = mockmemberRepository.findByName(member2.getName()).get();


        assertThat(response.getName()).isEqualTo("name2");
    }

    @Test
    void test_findByAll_returnsMembers(){
        Member member1 = new Member();
        member1.setName("name1");
        mockmemberRepository.save(member1);
        Member member2 = new Member();
        member2.setName("name2");
        mockmemberRepository.save(member2);


        List<Member> responselist = mockmemberRepository.findAll();


        assertThat(responselist.size()).isEqualTo(2);
    }
}
