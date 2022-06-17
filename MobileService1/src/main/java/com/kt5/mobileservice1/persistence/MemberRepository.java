package com.kt5.mobileservice1.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.mobileservice1.model.Member;

//JpaRepository를 상속받울 때는 entity 이름과 id로 설정한 속성의 자료형이 필요
public interface MemberRepository extends JpaRepository<Member, String> {
	//회원가입, 로그인 => 회원정보 가져오기, 회원정보 수정, 회원 탈퇴, Id 찾기등을 할려면
	//회원 가입을 하는 경우 이름의 중복 체크를 위한 메서드
	//기본키를 제외한 다른 칼럼을 가지고 조회를 할 때는 몇개가 나올지 모르기 때문에
	//리턴 타입은 List
	List<Member> findMemberByName(String name);
}
