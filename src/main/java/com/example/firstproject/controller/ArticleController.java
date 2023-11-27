package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleFormDTO formDTO) {
        log.info(formDTO.toString());

        Article article = formDTO.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model) {     // 매개변수로 id 받아오기
        log.info("id ==> " + id);   // id를 잘 받았는지 확인하는 로그 찍기
//        1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
//        2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
//        3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
//        1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
//        2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList", articleEntityList);
//        3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }

}
