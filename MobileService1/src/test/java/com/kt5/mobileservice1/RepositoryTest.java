package com.kt5.mobileservice1;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt5.mobileservice1.model.Member;
import com.kt5.mobileservice1.persistence.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	//Member에 데이터 삽입
	//@Test
	public void testRegisterMember() {
		String password = BCrypt.hashpw("123456", BCrypt.gensalt());
		
		Member member = Member.builder().email("kt5@kt.com").password(password).name("태오")
				.imageurl("to.png").build();
		memberRepository.save(member);
	}
	
	//회원 정보 가져오기 - 수정이나 로그인에서 사용
	//@Test
	public void testGetMember() {
		Optional<Member> optional = memberRepository.findById("kt5@kt.com");
		if(optional.isPresent()) {
			Member member = optional.get();
			System.out.println(member);
			//로그인은 이렇게 데이터를 가져와서 비밀번호를 비교
		}else {
			System.out.println("존재하지않는 데이터입니다.");
		}
	}
	
	//데이터 수정
	//@Test
	public void testUpdateMember() {
		String password = BCrypt.hashpw("111111", BCrypt.gensalt());
		
		Member member = Member.builder().email("kt2@kt.com").password(password).name("태오")
				.imageurl("user.png").build();
		memberRepository.save(member);
	}
	
	//데이터 삭제
	//@Test
	public void testDeleteMember() {
		
		Member member = Member.builder().email("kt55@kt.com").build();
		memberRepository.delete(member);
		//같음 memberRepository.deleteById(member.getEmail());
	}
}
