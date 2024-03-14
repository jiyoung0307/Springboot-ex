package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ArticleFormDTO {
    private Long id;
    private String title;
    private String content;

    /**
     * DTO를 엔티티로 변환하는 메서드
     * @return
     */
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
