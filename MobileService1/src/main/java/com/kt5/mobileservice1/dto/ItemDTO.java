package com.kt5.mobileservice1.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	private Long itemid;
	private String itemname;
	private int price;
	private String description;
	//업로드된 이미지 경로를 저장
	private String pictureurl;
	//파일을 받아오기 위한 변수
	private MultipartFile image;
	//Entity를 만들때는 Entity를외래키로 추가하지만 화면 입출력할 때는 필요한 데이터만 선언
	private String email;
	private String name;
}
