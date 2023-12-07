package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    /**
     * 모든 게시글 조회하기
     *
     * @return
     */
    public List<Article> index() {
        return articleRepository.findAll();
    }

    /**
     * 단일 게시글 조회하기
     *
     * @param id
     * @return
     */
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    /**
     * 게시글 등록하기
     *
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

    public Article update(Long id, ArticleFormDTO articleFormDTO) {
//        1. DTO -> 엔티티 변환하기
        Article article = articleFormDTO.toEntity();
        log.info("id: {}, article: {}", id, article.toString()); // 수정용 엔티티를 생성하고 로그를 찍어 보는 코드

//        2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);   // 대상 엔티티 찾기

//        3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {   // 잘목된 요청인지 판별
//            400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }
//        4. 업데이트 및 정상 응답(200)하기
        Article updated = articleRepository.save(article);  // article 엔티티 DB에 저장
        return updated; // 응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {
//        1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
//        2. 잘못된 요청 처리하기
        if (target == null) {
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }
//        3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }

    public List<Article> createArticles(List<ArticleFormDTO> dtos) {
//        1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
//        2. 엔티티 묶음(리스트)을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
//        3. 강제로 에러를 발생시키기
        articleRepository.findById(-1L) // id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); // 찾는 데이터가 없으면 예외 발생
//        4. 결과 값 반환하기
        return articleList;
    }
}
