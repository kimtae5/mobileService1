package com.kt5.mobileservice1.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//데이터베이스의 테이블과 연결된 entity로 설정
@Entity
//연결할 테이블 이름 설정
@Table(name = "tbl_member")

//Builder패턴으로 인스턴스를 생성하도록 해주는 어노테이션
@Builder
//모든 속성을 매개변수로 하는 생성자를 생성
@AllArgsConstructor
//매개변수가 없는 DefaultConstructor를 생성
@NoArgsConstructor
@Getter
//모든 속성의 ToString을 호출한 결과를 가지고 toString을 생성
@ToString
public class Member extends BaseEntity {
	@Id
	private String email;
	private String password;
	private String name;
	private String imageurl;
	private LocalDateTime lastlogindate;

}
