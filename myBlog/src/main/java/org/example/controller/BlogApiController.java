package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Article;
import org.example.dto.AddArticleRequest;
import org.example.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 받환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);

        /*응답코드
           - 200 OK : 요청이 성공적으로 수행되었음
           - 201 Created : 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
           - 400 Bad Request : 요청 값이 잘못되어 요청에 실패했음
           - 403 Forbidden : 권한이 없어 요청에 실패했음
           - 404 Not Found : 요청 값으로 찾은 리소스가 없어 요청에 실패헀음
           - 500 Internal Server Error : 서버 상에 문제가 있어 요청에 실패*/

    }
}
