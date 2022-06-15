package com.kt5.mobileservice1.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.mobileservice1.model.Member;

//JpaRepository를 상속받울 때는 entity 이름과 id로 설정한 속성의 자료형이 필요
public interface MemberRepository extends JpaRepository<Member, String> {
	//회원가입, 로그인 => 회원정보 가져오기, 회원정보 수정, 회원 탈퇴, Id 찾기등을 할려면
	//
}
