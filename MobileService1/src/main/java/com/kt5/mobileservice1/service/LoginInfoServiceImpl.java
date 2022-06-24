package com.kt5.mobileservice1.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kt5.mobileservice1.dto.LoginInfoDTO;
import com.kt5.mobileservice1.persistence.LoginInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LoginInfoServiceImpl implements LoginInfoService {
	private final LoginInfoRepository loginInfoRepository;

	@Override
	public Long registerLoginInfo(LoginInfoDTO dto) {
		//현재 날짜 및 시간을 설정
		dto.setRegdate(LocalDateTime.now());
		return null;
	}

}
