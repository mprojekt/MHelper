package com.services.impl;

import com.dao.impl.*;
import com.models.*;
import com.services.interfaces.ArticleServiceInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="as")
@Transactional
public class ArticleService implements ArticleServiceInterface{
    
    @Inject 
    private ArticleDao articleDao;
    @Inject 
    private CategoryDao categoryDao;
    @Inject 
    private TagDao tagDao;
    @Inject 
    private CommentDao commentDao;
    @Autowired(required=true)
    @Qualifier(value="mailService")
    private MailService mailService;

    @Override
    public List<Category> getAllCategory() {
        return this.categoryDao.getAll(); 
    }

    @Override
    public List<Tag> getAllTags() {
        return this.tagDao.getAll();
    }

    @Override
    public int create(ArticleForm form, String author) {
        if(form.getId() == 0){
            Article article = new Article();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Category category = doCategory(form.getCategory());
            List<Tag> tags = doTags(form.getTags(), true);

            article.setAuthor(author);
            article.setTags(tags);
            article.setCategory(category);
            article.setTitle(form.getTitle());
            article.setContent(form.getContent());
            article.setSource(form.getSource());
            article.setCreateDate(sdf.format(date));
            article.setVisible(true);            
            return this.articleDao.create(article);
        } else {
            Article article = this.articleDao.get(form.getId());

            Category category = doCategory(form.getCategory());
            List<Tag> tags = doTags(form.getTags(), false);
            
            article.setTags(tags);
            article.setCategory(category);
            article.setTitle(form.getTitle());
            article.setContent(form.getContent());
            article.setSource(form.getSource());
            
            this.articleDao.update(article);
            return form.getId();
        }
    }
    
    private List<Tag> doTags(String tagForm, boolean incr){
        List<Tag> tags = new ArrayList<>();
        String[] tmp = tagForm.split(",");
        for (String t : tmp) {
            Tag tag = this.tagDao.findTag(t);
            if(tag != null){
                if(incr){
                    tag.setNumber(tag.getNumber() + 1);
                    this.tagDao.update(tag);
                }
                tags.add(tag);
            } else {
                tag = new Tag();
                tag.setName(t);
                tag.setNumber(1);
                int id = this.tagDao.create(tag);
                tags.add(this.tagDao.get(id));
            }
        }
        return tags;
    }
    
    private Category doCategory(String categoryForm){
        Category c = this.categoryDao.findCategory(categoryForm);
        if(c != null){
            return c;
        } else {
            c = new Category();
            c.setName(categoryForm);
            int id = this.categoryDao.create(c);
            return this.categoryDao.get(id);
        }
    }

    @Override
    public Article get(int id) {
        return this.articleDao.get(id);
    }

    @Override
    public List<Tag> getTot() {        
        return this.tagDao.getAllTagsAsc();
    }

    @Override
    public List<DescribedList<SimpleUrlData>> getToc() {
        List<DescribedList<SimpleUrlData>> result = new ArrayList<>();
        List<Category> cat = this.getAllCategory();
        for (Category c : cat) {
            DescribedList<SimpleUrlData> tmp = new DescribedList<>(c.getName());
            tmp.setList(convertArticleToSUD(this.getArticleByCategory(c)));
            result.add(tmp);
        }
        return result;        
    }
    
    @Override
    public DescribedList<SimpleUrlData> getUnvisible() {
        DescribedList<SimpleUrlData> tmp = new DescribedList<>("Niewidoczne");
        tmp.setList(convertArticleToSUD(this.articleDao.getUnvisible()));
        return tmp;
    }
    
    private List<SimpleUrlData> convertArticleToSUD(List<Article> articleList){
        List<SimpleUrlData> tmp = new ArrayList<>();
        for (Iterator<Article> iterator = articleList.iterator(); iterator.hasNext();) {
            Article next = iterator.next();
            SimpleUrlData sdu = new SimpleUrlData(next.getTitle(), next.getId());
            sdu.setVisible(next.isVisible());
            tmp.add(sdu);
        }
        return tmp;
    }

    @Override
    public List<Article> getArticleByCategory(Category category) {
        return this.articleDao.getArticleByCategory(category);
    }

    @Override
    public List<SimpleUrlData> getArticleByTag(String tag) {
        Tag t = this.tagDao.findTag(tag);
        List<Article> result = new ArrayList<>();
        List<Article> tmp = this.articleDao.getByTag(t);
        for (int i = 0; i < tmp.size(); i++) {
            List<Tag> lt = tmp.get(i).getTags();
            if(lt.contains(t)){
                result.add(tmp.get(i));
            }
        }        
        return convertArticleToSUD(result); 
    }

    @Override
    public void addComment(CommentForm commentForm) {
        Comment comment = new Comment();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        Article article = this.articleDao.get(commentForm.getArticleId());
        
        comment.setName(commentForm.getName());
        comment.setText(commentForm.getText());
        comment.setNotify(commentForm.isNotify());
        comment.setMail(commentForm.getMail());
        comment.setCommentLevel(0);
        comment.setCreateDate(sdf.format(date));
        List<Comment> tmp = article.getComments();
        tmp.add(comment);
        article.setComments(tmp);
        
        this.articleDao.update(article);
    }

    @Override
    public void removeComment(int id) {
        this.commentDao.deleteById(id);
    }

    @Override
    public int plusComment(int id) {
        Comment c = this.commentDao.get(id);
        int tmp = c.getCommentLevel() + 1;
        c.setCommentLevel(tmp);
        this.commentDao.update(c);
        return tmp;
    }

    @Override
    public int minusComment(int id) {
        Comment c = this.commentDao.get(id);
        int tmp = c.getCommentLevel() - 1;
        c.setCommentLevel(tmp);
        this.commentDao.update(c);
        return tmp;
    }

    @Override
    public void setCommentToComment(String name, String text, int idComment, int idArticle) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        Comment comment = new Comment();
        comment.setName(name);
        comment.setText(text);
        comment.setNotify(false);
        comment.setMail(null);
        comment.setCommentLevel(0);
        comment.setCreateDate(sdf.format(date));
        
        Comment com = this.commentDao.get(idComment);
        List<Comment> tmp = com.getComment();
        tmp.add(comment);
        com.setComment(tmp); 
        
        if(com.isNotify())
            this.mailService.doMail(com, idArticle);
                
        this.commentDao.update(com);
    }

    @Override
    public Map<String, SimpleUrlData> getPrevAndNext(int idArticle) {
        Map<String, SimpleUrlData> result = new HashMap<>();
        List<DescribedList<SimpleUrlData>> ldl = getToc();
        List<SimpleUrlData> tmp = new ArrayList<>();
        
        for (DescribedList<SimpleUrlData> dl : ldl) {            
            if(!dl.getDescr().equals("Niewidoczne")){
                tmp.addAll(dl.getList());
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            if(tmp.get(i).getId() == idArticle){
                if(i == 0){ // pierwszy
                    result.put("prev", new SimpleUrlData("brak", 0));
                    if(i + 1 == tmp.size()){ // czy jedyny
                        result.put("next", new SimpleUrlData("brak", 0));
                    } else {
                        result.put("next", tmp.get(i + 1));
                    }                    
                } else if(i + 1 == tmp.size()){ // ostatni
                    result.put("prev", tmp.get(i - 1));
                    result.put("next", new SimpleUrlData("brak", 0));
                } else {
                    result.put("prev", tmp.get(i - 1));
                    result.put("next", tmp.get(i + 1));
                }
            }
        }
        
        return result;
    }

    @Override
    public void setVisible(int idArticle, boolean visible) {
        Article article = this.articleDao.get(idArticle);
        article.setVisible(visible);
        this.articleDao.update(article);
    }

    @Override
    public ArticleForm getArticleToEdit(int idArticle) {
        Article article = this.articleDao.get(idArticle);
        ArticleForm af = new ArticleForm();
        String tags = "";
        
        for (Tag t : article.getTags()) {
            tags += t.getName() + ",";
        } 
        
        af.setCategory(article.getCategory().getName());
        af.setContent(article.getContent());
        af.setSource(article.getSource());
        af.setTitle(article.getTitle());
        af.setId(idArticle);
        af.setTags(tags);
        return af;
    }

    @Override
    public List<SimpleUrlData> getArticleByFullText(String text) {
        List<SimpleUrlData> result = new ArrayList<>();
        try {
            List<Article> tmp = this.articleDao.searchArticleByFullText(text);
            result = convertArticleToSUD(tmp);            
        } catch (Exception ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } 

    @Override
    public List<SimpleUrlData> getLastArticle(int number) {
        return convertArticleToSUD(this.articleDao.getLastArticle(number));
    }

    

}
