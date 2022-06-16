package com.kt5.mobileservice1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//데이터베이스의 테이블과 연결된 entity로 설정
@Entity
//연결할 테이블 이름 설정 파일이름과 테이블이름이 똑같으면 필요없음
//@Table(name = "tbl_member")

//Builder패턴으로 인스턴스를 생성하도록 해주는 어노테이션
@Builder
//모든 속성을 매개변수로 하는 생성자를 생성
@AllArgsConstructor
//매개변수가 없는 DefaultConstructor를 생성
@NoArgsConstructor
//Getter, setter, toString을모두 생성 (entity는 setter안만듬)
@Data
//모든 속성의 ToString을 호출한 결과를 가지고 toString을 생성
//밑에 fetch로 인해 못가져오는 에러발생 안시키기위해  member.toString은 제외
@ToString(exclude = "member")
public class Item {
	@Id
	//기본키 값을 auto_increment나 sequence를 이용해서 자동 생성
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemid;
	
	@Column(length=100, nullable=false)//데이타베이스에는 적용안됨
	private String itemname;
	
	@Column
	private int price;
	
	@Column(length=200)
	private String description;
	
	@Column(length=255)
	private String pictureurl;
	
	//사용을 할 때 데이터를 가져오겠다는 옵션
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	
}
