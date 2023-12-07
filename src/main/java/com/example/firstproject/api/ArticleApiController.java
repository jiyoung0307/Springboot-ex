package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
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
    private ArticleService articleService;

    /**
     * 모든 게시글 조회하기
     * Get
     */
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    /**
     * 단일 게시글 조회하기
     * Get
     */
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable("id") Long id) {
        return articleService.show(id);
    }

    /**
     * 게시글 등록하기
     * POST
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleFormDTO articleFormDTO) {
        Article created = articleService.create(articleFormDTO);    // 서비스로 게시글 생성
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    /**
     * 데이터 전체를 수정할 경우
     * PATCH
     */
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id,
                                          @RequestBody ArticleFormDTO articleFormDTO) {
        Article updated = articleService.update(id, articleFormDTO);   // 서비스를 통해 게시글 수정
        return (updated != null) ?      // 수정되면 정상, 안되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * DELETE
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id) {
        Article deleted = articleService.delete(id);    // 서비스를 통해 게시글 삭제
        return (deleted != null) ?  // 삭제 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 여러 게시글 생성 요청 접수
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleFormDTO> dtos) {
        List<Article> createList = articleService.createArticles(dtos); // 서비스 호출
        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }   // transactionTest() 메서드 정의

}
