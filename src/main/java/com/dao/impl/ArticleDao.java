package com.dao.impl;

import com.dao.interfaces.ArticleDaoInterface;
import com.models.Article;
import com.models.Category;
import com.models.Tag;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ArticleDao extends CRUD<Article> implements ArticleDaoInterface{
    
    
    @Override
    public List<Article> getArticleByCategory(Category category) {
        Criteria c = getSession().createCriteria(Article.class);
        c.add(Restrictions.eq("category", category));
        c.add(Restrictions.eq("visible", true));
        c.addOrder(Order.asc("title"));
        return c.list();
    }

    @Override
    public List<Article> getUnvisible() {
        Criteria c = getSession().createCriteria(Article.class);
        c.add(Restrictions.eq("visible", false));
        c.addOrder(Order.asc("title"));
        return c.list();
    }

    @Override
    public List<Article> getByTag(Tag tag) {
        Criteria c = getSession().createCriteria(Article.class);
        //c.add(Restrictions.eq("tags", tag));
        c.add(Restrictions.eq("visible", true));
        c.addOrder(Order.asc("title"));
        return c.list(); 
    }

    @Override
    public List<Article> searchArticleByFullText(String text) throws Exception{
        try{         
            FullTextSession fullTextSession = Search.getFullTextSession(getSession());
            fullTextSession.createIndexer().startAndWait();

            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();
            org.apache.lucene.search.Query query = qb.keyword().onFields("content", "title").matching(text).createQuery();
            Query hibQuery = fullTextSession.createFullTextQuery(query, Article.class);
            List<Article> results = hibQuery.list();
                        
            return results;
        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<Article> getLastArticle(int number) {
        Criteria c = getSession().createCriteria(Article.class);
        c.add(Restrictions.eq("visible", true));
        c.addOrder(Order.desc("id"));
        c.setFirstResult(0);
        c.setMaxResults(number);
        return c.list();
    }

}
