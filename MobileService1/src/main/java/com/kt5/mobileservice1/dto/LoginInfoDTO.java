package com.kt5.mobileservice1.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {
	private Long logininfoid;
	private String email;
	private Double longitude;
	private Double latitude;
	private String address;
	private LocalDateTime regdate;
}
