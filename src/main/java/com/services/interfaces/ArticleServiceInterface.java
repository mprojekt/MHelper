package com.services.interfaces;

import com.models.*;
import java.util.List;
import java.util.Map;

public interface ArticleServiceInterface {
    
    public List<Category> getAllCategory();
    public List<Tag> getAllTags();
    public int create(ArticleForm form, String author);
    public Article get(int id);
    public List<Tag> getTot();
    public List<DescribedList<SimpleUrlData>> getToc(); 
    public DescribedList<SimpleUrlData> getUnvisible(); 
    public List<Article> getArticleByCategory(Category category);
    public List<SimpleUrlData> getArticleByTag(String tag);
    public void addComment(CommentForm commentForm);
    public void removeComment(int id);
    public int plusComment(int id);
    public int minusComment(int id);
    public void setCommentToComment(String name, String text, int idComment, int idArticle);
    public Map<String, SimpleUrlData> getPrevAndNext(int idArticle);
    public void setVisible(int idArticle, boolean visible);
    public ArticleForm getArticleToEdit(int idArticle); 
    public List<SimpleUrlData> getArticleByFullText(String text);
    public List<SimpleUrlData> getLastArticle(int number);
}
