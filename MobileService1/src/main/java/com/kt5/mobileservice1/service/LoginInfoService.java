package com.kt5.mobileservice1.service;

import com.kt5.mobileservice1.dto.LoginInfoDTO;
import com.kt5.mobileservice1.model.LoginInfo;

public interface LoginInfoService {
	//데이터 삽입을 위한 메서드
	public Long registerLoginInfo(LoginInfoDTO dto);
	
	
	//LoginInfoDTO를 LoginInfo로 변환해주는 메서드
	public default LoginInfo dtoToEntity(LoginInfoDTO dto) {
		LoginInfo loginInfo = LoginInfo.builder()
				.email(dto.getEmail())
				.address(dto.getAddress())
				.regdate(dto.getRegdate())
				.build();
		
		return loginInfo;
						
		
	}

}
