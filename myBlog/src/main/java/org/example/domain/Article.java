package org.example.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴 - 객체를 유연하고 직관적으로 생성할 수 있다. 어느 필드에 어떤 값이 들어가는지 명시적으로 파악가능
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
    // title에 어떤 값이, content에 어떤 값이 들어가는지 명시적으로 작성

}
