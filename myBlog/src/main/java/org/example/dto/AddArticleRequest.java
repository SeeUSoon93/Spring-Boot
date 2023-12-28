package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.Article;

// DTO는 계층끼리 데이터를 교환하기 위해 사용하는 객체
// DAO는 DB와 연결되고 데이터를 조회하고 수정하는데 사용하는 객체이기 때문에 로직이 포함
// DTO는 단순히 데이터를 옮기기 위한 전달자 이므로 비즈니스 로직이 포함하지 않음
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(String author){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
