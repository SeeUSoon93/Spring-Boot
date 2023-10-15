package com.mysite.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 스프링부트에서 시작을 담당하는 파일 - 프로젝트 생성시 프로젝트명+Application.java로 자동생성

@SpringBootApplication // 이 어노테이션을 통해 스프링부트의 모든 설정 관리
public class SbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbbApplication.class, args);
	}

}
