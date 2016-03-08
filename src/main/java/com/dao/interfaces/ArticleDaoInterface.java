package com.dao.interfaces;

import com.models.Article;
import com.models.Category;
import com.models.Tag;
import java.util.List;

public interface ArticleDaoInterface {
    
    public List<Article> getArticleByCategory(Category category);
    public List<Article> getUnvisible();
    public List<Article> getByTag(Tag tag);
    public List<Article> searchArticleByFullText(String text) throws Exception;
    public List<Article> getLastArticle(int number);
}
