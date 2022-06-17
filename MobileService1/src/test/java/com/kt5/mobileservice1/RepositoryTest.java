package com.kt5.mobileservice1;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kt5.mobileservice1.model.Item;
import com.kt5.mobileservice1.model.Member;
import com.kt5.mobileservice1.persistence.ItemRepository;
import com.kt5.mobileservice1.persistence.MemberRepository;


@SpringBootTest
public class RepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	//Member에 데이터 삽입
	//@Test
	public void testRegisterMember() {
		
		IntStream.rangeClosed(101, 1).forEach(i -> {
			
			String password = BCrypt.hashpw("i", BCrypt.gensalt());
			Member member = Member.builder().email("kt"+i+"@kt.com").password(password).name("태오"+i)
					.imageurl("to"+i+".png").build();
			memberRepository.save(member);
		});
	}
	
	//회원 정보 가져오기 - 수정이나 로그인에서 사용
	//@Test
	public void testGetMember() {
		Optional<Member> optional = memberRepository.findById("kt5@kt.com");
		if(optional.isPresent()) {
			Member member = optional.get();
			System.out.println(member);
			//로그인은 이렇게 데이터를 가져와서 비밀번호를 비교
		}else {
			System.out.println("존재하지않는 데이터입니다.");
		}
	}
	
	//데이터 수정
	//@Test
	public void testUpdateMember() {
		String password = BCrypt.hashpw("111111", BCrypt.gensalt());
		
		Member member = Member.builder().email("kt2@kt.com").password(password).name("태오")
				.imageurl("user.png").build();
		memberRepository.save(member);
	}
	
	//데이터 삭제
	//@Test
	public void testDeleteMember() {
		
		Member member = Member.builder().email("kt5@kt.com").build();
		memberRepository.delete(member);
		//같음 memberRepository.deleteById(member.getEmail());
	}
	
	//이름으로 데이터 조회
	//@Test
	public void testFindName() {
		String name = "태오";
		List<Member> list = memberRepository.findMemberByName(name);
		System.out.println(list);
		//주소로 받아옴
		if(list.size() > 0) {
			System.out.println("데이터가 존재합니다.");
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		
		name = "dd";
		list = memberRepository.findMemberByName(name);
		System.out.println(list);
		
		System.out.println(list.size() > 0 ? "데이터가 존재합니다." : "데이터가 존재하지 않습니다." );
	}
	
	@Autowired
	private ItemRepository itemRepository;
	//Item 삽입을 테스트
	
	//@Test
	public void testinsertItem() {
		//외래키를 생성
		Member member = Member.builder().email("kt5@kt.com").build();
	
		IntStream.rangeClosed(1, 100).forEach( i -> {
			Item item = Item.builder().itemname("사과" + i).price(i * 1100).description("비타민" + i)
					.pictureurl("apple" + i + ".png").member(member).build();
			itemRepository.save(item);
		});
	}
	
	//데이터 전체보기 테스트
	//@Test
	public void getAll() {
		List<Item> list = itemRepository.findAll();
		System.out.println(list);
	}
	
	//페이징과 정렬
	//@Test
	public void getPage() {
		Sort sort = Sort.by("itemid").descending();
		Pageable pageable = PageRequest.of(0,5, sort);
		Page<Item> page = itemRepository.findAll(pageable);
		page.get().forEach(item ->{
			System.out.println(item);
		});	
	}
	
	//외래키를  이용한 조회
	//@Test
	public void getFindMember() {
		Member member = Member.builder().email("kt5@kt.com").build();
		List<Item> list = itemRepository.findItemByMember(member);
		System.out.println(list.size() > 0 ? list : "데이터가 존재하지 않습니다." );
	}
	
	//데이터 1개 가져오기
	//@Test
	public void getItem() {
		Optional<Item> item = itemRepository.findById(100L);
		/*
		if (item.isPresent()) {
			System.out.println(item);
		}else {
			System.out.println("데이터 존재x");
		}
		*/
		System.out.println(item.isPresent() ? item : "데이터 존재x" );
	}
	
	//데이터 수정
	//@Test
	public void updateItem() {
		Member member = Member.builder().email("kt5@kt.com").build();
		
		Item item = Item.builder().itemid(100L).itemname("배").price(2000).pictureurl("pear.png")
				.member(member).build();
		itemRepository.save(item);
	}
	
	//데이터 삭제
	//@Test
	public void deleteItem() {
		Item item = Item.builder().itemid(100L).build();
		itemRepository.delete(item);
	}
	
	
}
