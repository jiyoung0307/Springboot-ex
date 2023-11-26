package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired  // 스프링 부트가 미리 생성해 놓은 리파지토리 객체 주입(DI)
    private ArticleRepository articleRepository;        /** articleRepository 선언 */

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleFormDTO formDTO) {                    /** 폼 데이터를 DTO로 받기 */
        System.out.println("formDTO.toString() ==> " + formDTO.toString());  /** DTO에 폼 데이터가 잘 담겼는지 확인*/
//         1. DTO를 엔티티로 변환
        Article article = formDTO.toEntity();
        System.out.println("DTO가 엔티티로 잘 변환되는지 확인 ==> " + article.toString());
//         2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println("article이 DB에 잘 저장되는지 확인 ==> " + saved.toString());
        return "";
    }

}
