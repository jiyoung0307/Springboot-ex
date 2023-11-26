package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleFormDTO {
    private String title;
    private String content;

//    전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleFormDTO() {}
    public ArticleFormDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleFormDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
//        DTO 객체를 엔티티로 변환
        return new Article(null, title, content);   // Article 클래스의 생성자 형식에 맞게 작성
    }
}
