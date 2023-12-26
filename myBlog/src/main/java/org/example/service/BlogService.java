package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Article;
import org.example.dto.AddArticleRequest;
import org.example.dto.UpdateArticleRequest;
import org.example.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글 목록 조회 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 글 하나 조회 메서드
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+id));
    }

    // 글 삭제 메서드
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // 글 수정 메서드
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+ id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
