package com.example.firstproject.api;

import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import org.junit.jupiter.api.Test;  // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;   // 앞으로 사용할 수 있는 패키지 임포트

@SpringBootTest
class ArticleApiControllerTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
//        1. 예상 데이터
//        2. 실제 데이터
        List<Article> articles = articleService.index();
//        3. 예상 데이터와 실제 데이터 비교해 검증하기
    }
}