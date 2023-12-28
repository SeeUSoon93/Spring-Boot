package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Article;
import org.example.dto.AddArticleRequest;
import org.example.dto.ArticleResponse;
import org.example.dto.UpdateArticleRequest;
import org.example.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 받환하는 컨트롤러
@RequestMapping(produces = "application/json;charset=UTF-8")
public class BlogApiController {

    private final BlogService blogService;

    // 게시글 생성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){
        Article savedArticle = blogService.save(request, principal.getName());
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

    // 게시글 목록 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        // ArticleResponse 객체로 매핑하는 작업을 수행
        // ArticleResponse 클래스의 생성자를 사용하여 블로그 글을 ArticleResponse 객체로 변환

        return ResponseEntity.ok()
                .body(articles);

    }

    // 글 하나 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
}
