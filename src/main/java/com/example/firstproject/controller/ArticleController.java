package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleFormDTO formDTO) {                    /** 폼 데이터를 DTO로 받기 */
        System.out.println("formDTO.toString() ==> " + formDTO.toString());  /** DTO에 폼 데이터가 잘 담겼는지 확인*/
        return "";
    }

}
