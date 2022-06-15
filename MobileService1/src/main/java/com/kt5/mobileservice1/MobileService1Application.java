package com.kt5.mobileservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Java에서는 @가 붙은 단어를 Annotation 이라고 하고 Python에서는 Decorator라고 함
//자주 사용하는 코드를 하나의 클래스로 만든 후 이 클래스의 수행 코드를 예약어 형태로 만든 것
//이언 코드를 Annotation을 이용해서 만들 수도 있고 AOP를 이용해서 걸정하는 것도 가능
@SpringBootApplication
@EnableJpaAuditing
public class MobileService1Application {

	public static void main(String[] args) {
		SpringApplication.run(MobileService1Application.class, args);
	}

}
