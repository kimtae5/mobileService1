package com.kt5.mobileservice1.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.kt5.mobileservice1.dto.LoginInfoDTO;
import com.kt5.mobileservice1.model.LoginInfo;
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
		
		//위도와 경도 가져오기
		Double longitude = dto.getLongitude();
		Double latitude = dto.getLatitude();
		
		System.out.println("service :" + dto);
		
		//위도와 경도가 전송된 경우에만 작업을 수행
		if(longitude != null && latitude != null) {
			try {
				//전송받을 URL생성
				URL url = new URL("https://dapi.kakao.com/v2/local/geo/coord2address.json?" 
				+ "input_coord=WGS84&x=" + longitude + "&y=" + latitude);
				
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setConnectTimeout(30000);
				con.setUseCaches(false);
				
				con.setRequestProperty("Authorization", "KakaoAK 47b777871f1a42f5b367d563a2ca1ed0");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
			
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					sb.append(line + "\n");
				}
				br.close();
				con.disconnect();
				
				if(sb.toString().length() > 0) {
					JSONObject object = new JSONObject(sb.toString());
					
					System.out.println("object :" + object);
					//검색된 데이터 개수 가져오기
					JSONObject meta = object.getJSONObject("meta");
					Integer total_count = meta.getInt("total_count");
					if(total_count > 0) {
						JSONArray documents = object.getJSONArray("documents");
						//첫번째 데이터 가져오기
						JSONObject first = documents.getJSONObject(0);
						
						JSONObject road_address = new JSONObject();
						try {
							road_address = first.getJSONObject("road_address");
						}catch(Exception e) {
							road_address = first.getJSONObject("address");
							System.out.println(" 에러:" + e.getLocalizedMessage());
						}
						
						String address_name = road_address.getString("address_name");
						System.out.println("주소:" + address_name);
						//주소 설정
						dto.setAddress(address_name);
					}
				}
			}catch(Exception e){
				System.out.println("데이터 가져오기 파싱 에러:" + e.getLocalizedMessage());
			}
		}
		//데이터 변환 후 삽입
		LoginInfo loginInfo = dtoToEntity(dto);
		loginInfoRepository.save(loginInfo);
		return loginInfo.getLogininfoid();
	}
}
