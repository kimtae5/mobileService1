package com.kt5.mobileservice1.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.mobileservice1.model.Item;
import com.kt5.mobileservice1.model.Member;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	//Member를 이용해서 Member가 작성한 모든 Item을 조회하는 메서드
	List<Item> findItemByMember(Member member);

}
