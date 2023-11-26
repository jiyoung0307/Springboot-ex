package com.example.firstproject.dto;

public class ArticleFormDTO {
    private String title;
    private String content;

//    전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleFormDTO() {}
    public ArticleFormDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleFormDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
