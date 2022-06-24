package com.kt5.mobileservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt5.mobileservice1.dto.MemberDTO;
import com.kt5.mobileservice1.dto.ResponseMemberDTO;
import com.kt5.mobileservice1.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerMember(MemberDTO dto){
		System.out.println("dto:" + dto.toString());
		ResponseMemberDTO response = null;
		try {
			//데이터 삽입처리
			String email = memberService.registerMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//로그인처리
			MemberDTO result = memberService.loginMember(dto);
			if(result == null) {
				response = ResponseMemberDTO.builder().error("비밀번호가 잘못되었습니다.").build();
			}else{
				response = ResponseMemberDTO.builder()
						.email(result.getEmail())
						.name(result.getName())
						.imageurl(result.getImageurl())
						.regdate(result.getRegdate())
						.moddate(result.getModdate())
						.lastlogindate(result.getLastlogindate())
						.build();
			}
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//회원정보가져오기
			MemberDTO result = memberService.getMember(dto);
			if(result == null) {
				response = ResponseMemberDTO.builder().error("비밀번호가 잘못되었습니다.").build();
			}else{
				response = ResponseMemberDTO.builder()
						.email(result.getEmail())
						.name(result.getName())
						.imageurl(result.getImageurl())
						.regdate(result.getRegdate())
						.moddate(result.getModdate())
						.lastlogindate(result.getLastlogindate())
						.build();
			}
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//데이터 수정처리
			String email = memberService.updateMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//데이터 삭제처리
			String email = memberService.deleteMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	//안드로이드에서는 서버의 이미지를 다운로드 받아야 출력할 수 있기 때문에 이미지를
	//다운로드 받을 수 있는 요청을 생성해야 합니다.
	@GetMapping("/download")
	public ResponseEntity<Object> download(String path){
		return memberService.download(path);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
