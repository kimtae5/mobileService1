package com.kt5.mobileservice1.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;

import com.kt5.mobileservice1.dto.MemberDTO;
import com.kt5.mobileservice1.model.Member;

public interface MemberService {
	//데이터 삽입
	public String registerMember(MemberDTO dto);
	public MemberDTO loginMember(MemberDTO memberDTO);
	public MemberDTO getMember(MemberDTO memberDTO);
	public String updateMember(MemberDTO dto);
	public String deleteMember(MemberDTO dto);
	public ResponseEntity<Object> download(String path);
	
	//DTO 클래스의 객체를 Model 클래스의 객체로 변환
	public default Member dtoToEntity(MemberDTO dto) {
		String password = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		Member member = Member.builder()
				.email(dto.getEmail())
				.name(dto.getName())
				.password(password)
				.imageurl(dto.getImageurl())
				.lastlogindate(dto.getLastlogindate())
				.build();
		return member;
	}
	
	//Model 객체를 DTO 클래스로 변환
	public default MemberDTO entityToDTO(Member member) {
		MemberDTO dto = MemberDTO.builder()
				.email(member.getEmail())
				.name(member.getName())
				.imageurl(member.getImageurl())
				.regdate(member.getModDate())
				.moddate(member.getModDate())
				.lastlogindate(member.getLastlogindate())
				.build();
		return dto;
	}
}
