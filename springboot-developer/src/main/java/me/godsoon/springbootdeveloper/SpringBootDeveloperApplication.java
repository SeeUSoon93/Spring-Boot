package me.godsoon.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 자바의 main()메서드와 같은 역할. 스프링 부트가 시작됨
@SpringBootApplication // 스프링부트 사용에 필요한 기본 설정을 해줌
public class SpringBootDeveloperApplication {
    public static void main(String[] args){
        // 애플리케이션을 실행하는 메서드
        // SpringApplication.run(메인클래스로 사용할 클래스, 커맨드 라인의 인수를 전달)
        SpringApplication.run(SpringBootDeveloperApplication.class, args);

    }
}
