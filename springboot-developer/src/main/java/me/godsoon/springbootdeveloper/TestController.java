package me.godsoon.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController // 라우터(HTTP 요청과 메서드를 연결하는 장치) 역할을 하는 애터네이션.
// RestController에는 @Controller 애터네이션과 @ResponseBody 애너테이션이 있음.
// Controller에는 @Component 애너테이션이 있음. 이를 통해 빈으로 등록됨(Service, Repository등도 마찬가지)
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }
}
