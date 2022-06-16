package com.kt5.mobileservice1;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt5.mobileservice1.dto.ItemDTO;
import com.kt5.mobileservice1.dto.MemberDTO;
import com.kt5.mobileservice1.dto.PageRequestItemDTO;
import com.kt5.mobileservice1.dto.PageResponseItemDTO;
import com.kt5.mobileservice1.service.ItemService;
import com.kt5.mobileservice1.service.MemberService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private MemberService memberService;
	
	//회원 가입 테스트
	//@Test
	public void testRegisterMember() {
		// 처음 추가를 할 때는 성공해야 하고 email 과 name 을 중복된 데이터로 설정해서 확인
		MemberDTO dto = MemberDTO.builder()
				.email("kst5@kt.com")
				.password("123456")
				.name("태오")
				.imageurl("te.png")
				.build();
		String result = memberService.registerMember(dto);
		System.out.println(result);
	}
	
	//회원정보 가져오기
	//@Test
	public void testGetMember() {
		// 처음 추가를 할 때는 성공해야 하고 email 과 name 을 중복된 데이터로 설정해서 확인
		MemberDTO dto = MemberDTO.builder()
				.email("kt5@kt.com")
				.build();
		MemberDTO result = memberService.getMember(dto);
		System.out.println(result);
	}
	
	//로그인 테스트
	//@Test
	public void testLoginMember() {
		MemberDTO dto = MemberDTO.builder()
				.email("kt5@kt.com")
				.password("123456")
				.build();
		MemberDTO result = memberService.loginMember(dto);
		System.out.println(result);

		//오늘 날짜를 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(date);
		try {
			FileOutputStream fos = new FileOutputStream(
					"D:\\taeokim\\springboot\\sts4workspace\\MobileService1\\log\\"+ d + ".txt", true);
			PrintWriter pw = new PrintWriter(fos);
			pw.println("내용");
			pw.flush();
			pw.close();
			
		}catch(Exception e) {}
	
	}
	
	//멤버 수정테스트
	//@Test
	public void updateMember() {
		MemberDTO dto = MemberDTO.builder()
				.email("kt5@kt.com")
				.password("12")
				.name("티오")
				.imageurl("apap.png")
				.build();
		String result = memberService.updateMember(dto);
		System.out.println(result);
	}
	
	//멤버 삭제테스트
	//@Test
	public void deleteMember() {
		MemberDTO dto = MemberDTO.builder()
				.email("kt5@kt.com")
				.build();
		String result = memberService.deleteMember(dto);
		System.out.println(result);
	}
	
	
	@Autowired
	private ItemService itemService;
	
	//데이터 삽입
	//@Test
	public void testInsertItem() {
		for(int i = 0; i < 100; i++) {
			ItemDTO dto = ItemDTO.builder()
					.itemname("apple..." + i)
					.price(3000)
					.description("사과..." + i)
					.pictureurl("apple_" + i +".png")
					.email("kt100@kt.com")
					.build();
			Long itemid = itemService.registerItem(dto);
			System.out.println(itemid);
		}
	}
	
	//데이터 1개 가져오기
	//@Test
	public void testGetItem() {
		ItemDTO dto = ItemDTO.builder()
				.itemid(101L)
				.build();
		System.out.println(itemService.getItem(dto));
		dto = ItemDTO.builder()
				.itemid(10L)
				.build();
		System.out.println(itemService.getItem(dto));
	}
	
	//페이지단위로 가져오기
	//@Test
	public void testGetList() {
		PageRequestItemDTO dto = PageRequestItemDTO.builder()
				.page(2)
				.size(5)
				.build();
		PageResponseItemDTO result = itemService.getList(dto);
		System.out.println(result);
	}
	
	//데이터 수정
	//@Test
	public void testUpdateItem() {
		ItemDTO dto = ItemDTO.builder()
				.itemid(101L)
				.itemname("apple...100")
				.price(3000)
				.description("사과...100")
				.pictureurl("apple_100.png")
				.email("kt100@kt.com")
				.build();
		Long itemid = itemService.registerItem(dto);
		System.out.println(itemid);
	}
		
	//데이터 삭제
	//@Test
	public void testdeleteItem() {
		ItemDTO dto = ItemDTO.builder()
				.itemid(101L)
				.build();
		Long itemid = itemService.deleteItem(dto);
		System.out.println(itemid);
	}
		
		

}
