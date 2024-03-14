package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test   // 해당 메서드가 테스트 코드임을 선언
    void index() {
        // 1. 예상 데이터
        Article a = new Article(3L, "가가가가", "1111");
        Article b = new Article(4L,"나나나나","2222");
        Article c = new Article(5L, "다다다다","3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 3L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -3L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }


    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleFormDTO articleFormDTO = new ArticleFormDTO(null, title, content);
        Article expected = new Article(6L, title, content);
        // 2. 실제 데이터
        Article article = articleService.create(articleFormDTO);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 6L;
        String title = "라라라라";
        String content = "4444";
        ArticleFormDTO articleFormDTO = new ArticleFormDTO(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(articleFormDTO);
        // 3. 비교 및 검증
        assertEquals(expected, article);

    }
}