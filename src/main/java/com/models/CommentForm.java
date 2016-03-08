package com.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;


public class CommentForm {
      
    @NotNull(message = "Imię nie może być puste.")
    @Size(min = 3, max = 20, message = "Imię nie może być kródsze niż 3 znaki i dłuższe niż 20 znaków.")
    private String name;
    
    
    @Email(message = "Adres e-mail musi być prawidłowym adresem e-mail.")
    @Size( max = 50, message = "Adres e-mail nie może dłuższy niż 50 znaków.")
    private String mail;
    
    @NotNull(message = "Treść komentarza nie może być pusta.")
    @Size(min = 5, max = 1000, message = "Treść komentarza nie może być pusta, ani przekraczać 1000 znaków.")
    private String text;
    
    private int id;
    private boolean notify; 
    private int articleId;

    public CommentForm() {
        this.notify = false;
        this.mail = null;
    }

    public CommentForm(int articleId) {
        this();
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
