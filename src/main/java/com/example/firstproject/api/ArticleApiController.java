package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AbstractTraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 모든 게시글 조회하기
     * Get
     */
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    /**
     * 단일 게시글 조회하기
     * Get
     */
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable("id") Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    /**
     * 데이터 생성
     * POST
     */
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleFormDTO articleFormDTO) {
        Article article = articleFormDTO.toEntity();
        return articleRepository.save(article);
    }

    /**
     * 데이터 전체를 수정할 경우
     * PATCH
     */
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable("id") Long id,
//                                          @RequestBody ArticleFormDTO articleFormDTO) {
////        1. DTO -> 엔티티 변환하기
//        Article article = articleFormDTO.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
////        2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//
////        3. 잘못된 요청 처리하기
//        if (target == null || id != article.getId()) {   // 잘목된 요청인지 판별
////            400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);    // ResponseEntity 반환
//        }
////        4. 업데이트 및 정상 응답(200)하기
//        Article updated = articleRepository.save(article);  // article 엔티티 DB에 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답
//    }

    /**
     * 일부 데이터만 수정할 경우
     * PATCH
     */
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id,
                                          @RequestBody ArticleFormDTO articleFormDTO) {
//        1. DTO -> 엔티티 변환하기
        Article article = articleFormDTO.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

//        2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

//        3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {   // 잘목된 요청인지 판별
//            400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);    // ResponseEntity 반환
        }
//        4. 업데이트 및 정상 응답(200)하기
        target.patch(article);  // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target);  // 수정 내용 DB에 최종 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답
    }


    /**
     * DELETE
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id) {
//        1. DB에서 대상 엔티티가 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);
//        2. 대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리하기
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
//        3. 대상 엔티티가 있으면 삭제하고 정상 응답(200) 반환하기
        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
