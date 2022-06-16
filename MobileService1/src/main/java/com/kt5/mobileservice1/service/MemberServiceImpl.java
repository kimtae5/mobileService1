package com.kt5.mobileservice1.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.kt5.mobileservice1.dto.MemberDTO;
import com.kt5.mobileservice1.model.Member;
import com.kt5.mobileservice1.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;

	@Override
	public String registerMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		//이메일 중복 체크
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		if (optional.isPresent()) {
			return "존재하는 이메일";
		}
		// 이름 중복체크
		List<Member> list = memberRepository.findMemberByName(dto.getName());
		if(list.size() > 0) {
			return "존재하는 이름";
		}
		
		memberRepository.save(member);
			return member.getEmail();
	}

	@Override
	public MemberDTO loginMember(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
		Optional <Member> optional = memberRepository.findById(memberDTO.getEmail());
		//존재하는 이메일
		if(optional.isPresent()) {
			//비밀번호 확인
			Member member = optional.get();
			if(BCrypt.checkpw(memberDTO.getPassword(), member.getPassword())) {
				//로그인에 성공했을 때 로그인 한 시간을 업데이트
				//UTC(시간차있음) 시간으로 나오면 아래와 같이
				ZonedDateTime nowUTC =ZonedDateTime.now(ZoneId.of("UTC"));
				LocalDateTime now = nowUTC.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
				System.out.println(now);
				
				Member updateMember = Member.builder()
						.email(member.getEmail())
						.password(member.getPassword())
						.imageurl(member.getImageurl())
						.name(member.getName())
						.lastlogindate(now)
						.build();
				
				memberRepository.save(updateMember);
				
				return entityToDTO(member);
			}else {
				return null;
			}
		}else {
			//존재하지 않는 이메일
			return null;
		}
	}

	@Override
	public MemberDTO getMember(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
				Optional <Member> optional = memberRepository.findById(memberDTO.getEmail());
				//존재하는 이메일
				if(optional.isPresent()) {
					//비밀번호 확인
					Member member = optional.get();
					return entityToDTO(member);
				}else {
					
					return null;
				}
	}

	@Override
	public String updateMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public String deleteMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.delete(member);
		return member.getEmail();
	}

}
