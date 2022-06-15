package com.kt5.mobileservice1.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

//테이블로 생성하지 말고 매핑 정보만 사용하겠다라는 의미
@MappedSuperclass
//Entity 의 변경 사항이 발생했을때 작업을 수행
@EntityListeners(value= {AuditingEntityListener.class})

//Lombok 라이브러리에서 속성의 Getter메서드를 만들어주는 어노테이션
@Getter
abstract class BaseEntity {
	//생성날짜를 이용
	@CreatedDate
	//테이블에 만들어 질 때는 regdate 라는 컬럼으로 생성되고 수정x
	@Column(name="regdate" , updatable=false)
	private LocalDateTime regDate;
	//마지막 수정 날짜 이용
	@LastModifiedDate
	//테이블이 만들어 질 때는 modDate란 칼럼으로 생성
	@Column(name="date")
	private LocalDateTime modDate;
}
