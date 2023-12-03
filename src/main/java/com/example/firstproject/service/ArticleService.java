package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    /**
     * 모든 게시글 조회하기
     * @return
     */
    public List<Article> index() {
        return articleRepository.findAll();
    }

    /**
     * 단일 게시글 조회하기
     * @param id
     * @return
     */
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    /**
     * 게시글 등록하기
     * @param articleFormDTO
     * @return
     */
    public Article create(ArticleFormDTO articleFormDTO) {
//        1. DTO를 엔티티로 변환해 article 객체에 저장
        Article article = articleFormDTO.toEntity();
//        article 객체에 id가 존재한다면 null을 반환
        if (article.getId() != null) {
            return null;
        }
//        2. 리파지터리에 article을 DB에 저장하도록 함
        return articleRepository.save(article);
    }
}
