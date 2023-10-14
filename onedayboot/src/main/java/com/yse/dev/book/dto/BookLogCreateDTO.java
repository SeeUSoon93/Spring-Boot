package com.yse.dev.book.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookLogCreateDTO {
    // 책의 고유 ID. 반드시 양수여야 합니다.
    @NonNull
    @Positive
    private Integer bookId;

    // 책에 대한 코멘트 또는 설명
    @NonNull
    private String comment;

    // 읽은 페이지 수 (선택적)
    private Integer page;
}
