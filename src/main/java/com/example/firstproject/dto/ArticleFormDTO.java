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
    private String title;
    private String content;

    public Article toEntity() {
//        DTO 객체를 엔티티로 변환
        return new Article(null, title, content);   // Article 클래스의 생성자 형식에 맞게 작성
    }
}
