package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/** 1. 엔티티 선언 */
@Entity
public class Article {
    @Id
    /** 2. 엔티티의 대푯값 지정 */
    @GeneratedValue
    /** 3.자동 생성 기능 추가(숫자가 자동으로 매겨짐) */
    private Long id;

    @Column
    /** 2. title 필드 선언, DB 테이블의 title 열과 연결됨 */
    private String title;

    @Column
    /** 2. content 필드 선언, DB 테이블의 content 열과 연결됨  */
    private String content;

//    Article 생성자 추가
//    Article 객체의 생성 및 초기화를 위해
    public Article() {}

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    //    toString() 메서드 추가
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
