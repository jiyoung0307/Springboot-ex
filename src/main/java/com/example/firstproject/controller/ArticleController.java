package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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

    /**
     * 새 글작성하기
     *
     * @return
     */
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    /**
     * 폼 데이터 받기(새 글 작성하기)
     *
     * @param formDTO
     * @return
     */
    @PostMapping("/articles/create")
    public String createArticle(ArticleFormDTO formDTO) {
        log.info(formDTO.toString());

        Article article = formDTO.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    /**
     * 단일 조회
     *
     * @param id
     * @param model
     * @return
     */
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

    /**
     * 목록 조회
     *
     * @param model
     * @return
     */
    @GetMapping("/articles")
    public String index(Model model) {
//        1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
//        2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList", articleEntityList);
//        3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }

    /**
     * Edit 요청을 받아 데이터 가져오기
     *
     * @return
     */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
//        수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
//        모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
//        뷰 페이지 설정하기
        return "articles/edit";
    }


    /**
     * 수정 데이터 받아오기
     *
     * @param formDTO
     * @return
     */
    @PostMapping("/articles/update")
    public String update(ArticleFormDTO formDTO) {
        log.info(formDTO.toString());
//        1. DTO를 엔티티로 변환하기
        Article articleEntity = formDTO.toEntity();
        log.info(articleEntity.toString());
//        2. 엔티티를 DB에 저장하기
//        2-1. 기존 데이터 값을 갱신하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
//        2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            articleRepository.save(articleEntity);  // 엔티티를 DB에 저장(갱신)
        }
//        3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    /**
     * 삭제하기
     */
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable("id") Long id) {   // id를 매개변수로 가져오기
        log.info("삭제 요청이 들어왔다!!");
//        1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);   // 데이터 찾기
        log.info("target.toString() ==> " + target.toString());    // target에 데이터가 있는지 확인하는 로그 찍기
//        2. 대상 엔티티 삭제하기
        if(target != null) {    // 삭제할 대상이 있는지 확인
            articleRepository.delete(target);   // delete() 메서드로 대상 삭제
        }
//        3. 결과 페이지로 리다이렉트하기56
        return "redirect:/articles";
    }

}



