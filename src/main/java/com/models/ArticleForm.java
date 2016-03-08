package com.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleForm {
    
    private int id;
    @NotNull(message = "Tytuł nie może być pusty!")
    @Size(min = 5, max = 200, message = "Tytuł musi mieć min 5, a maks 200 znaków!")
    private String title;
    @NotNull(message = "Kategoria nie może być pusta!")
    @Size(min = 1, max = 20, message = "Kategoria musi mieć maks 20 znaków!")
    private String category;
    @NotNull(message = "Artykuł nie może być pusty!")
    @Size(min = 5, max = 20000, message = "Artykuł nie może przekraczać 20000 znaków!")
    private String content;
    @NotNull(message = "Artykuł musi mieć jakieś tagi!")
    @Size(min = 1, message = "Artykuł musi mieć jakieś tagi")
    private String tags;
    @Size(max = 100, message = "Źródło nie może przekraczać 100 znaków!")
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
}
