package com.controllers;

import com.models.Article;
import com.models.Category;
import com.models.Comment;
import com.models.CommentForm;
import com.models.DescribedList;
import com.models.SimpleUrlData;
import com.models.Tag;
import com.services.interfaces.ArticleServiceInterface;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {
    
    @Autowired(required=true)
    @Qualifier(value="as")
    private ArticleServiceInterface articleService;
    
    @RequestMapping(value = "/rest/tag", method = RequestMethod.GET)
    public @ResponseBody List<SimpleUrlData> getArticleByTag(HttpServletRequest request, HttpServletResponse response){
        String tagName = request.getParameter("tag"); 
        
        if(tagName.equals("")){
            return null;
        } else {
            response.setContentType("application/json;charset=UTF-8");        
            return this.articleService.getArticleByTag(tagName);
        }
    }
    
    @RequestMapping(value = "/rest/comment/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteComment(HttpServletRequest request, HttpServletResponse response){
        int idCommment = Integer.parseInt(request.getParameter("idComment")); 
        
        if(idCommment == 0){
            return null;
        } else {
            this.articleService.removeComment(idCommment);        
            return "ok";
        }
    }
    
    @RequestMapping(value = "/rest/comment/plus", method = RequestMethod.POST)
    private @ResponseBody String plusComment(HttpServletRequest request){
        int idCommment = Integer.parseInt(request.getParameter("idComment"));
        
        if(idCommment == 0){
            return null;
        } else {
            int result = this.articleService.plusComment(idCommment);        
            return "" + result;
        }
    }
    
    @RequestMapping(value = "/rest/comment/minus", method = RequestMethod.POST)
    private @ResponseBody String minusComment(HttpServletRequest request, HttpServletResponse response){
        int idCommment = Integer.parseInt(request.getParameter("idComment")); 
        
        if(idCommment == 0){
            return null;
        } else {
            int result = this.articleService.minusComment(idCommment);        
            return "" + result; 
        }
    }
    
    @RequestMapping(value = "/rest/article", method = RequestMethod.GET)
    public @ResponseBody byte[] getArticle(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        int idArticle = Integer.parseInt(request.getParameter("idArticle"));
        
        if(idArticle == 0){
            return null;
        } else {
            Article article = this.articleService.get(idArticle);
            response.setContentType("application/json;charset=UTF-8");
            return createJSONfromArticle(article).getBytes("UTF-8");
        }
    }
    
    @RequestMapping(value = "/rest/toc", method = RequestMethod.GET)
    public @ResponseBody List<DescribedList<SimpleUrlData>> getArticleToc(HttpServletRequest request, HttpServletResponse response){              
        response.setContentType("application/json");
        return this.articleService.getToc();
    }
    
    @RequestMapping(value = "/rest/category", method = RequestMethod.GET)
    public @ResponseBody List<Category> getAllCategory(HttpServletRequest request, HttpServletResponse response){              
        response.setContentType("application/json;charset=UTF-8");
        return this.articleService.getAllCategory();
    }
    
    @RequestMapping(value = "/rest/tags", method = RequestMethod.GET)
    public @ResponseBody List<Tag> getAllTags(HttpServletRequest request, HttpServletResponse response){              
        response.setContentType("application/json;charset=UTF-8");
        return this.articleService.getAllTags(); 
    }
 
    @RequestMapping(value = "/rest/search", method = RequestMethod.GET)
    public @ResponseBody List<SimpleUrlData> search(HttpServletRequest request, HttpServletResponse response){
        String text = request.getParameter("searchText"); 
        
        if(text.equals("")){
            return null;
        } else {
            response.setContentType("application/json;charset=UTF-8");
            return this.articleService.getArticleByFullText(text);
        }
    }
    
    @RequestMapping(value = "/rest/comment", method = RequestMethod.POST)
    public @ResponseBody String commentArticle(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        String mail = request.getParameter("mail");
        boolean notify = Boolean.parseBoolean(request.getParameter("notify"));
        int idArticle = Integer.parseInt(request.getParameter("idArticle"));
        
        if((name.equals("") || (text.equals("")) || (idArticle == 0))){
            return "Error! Brak wymaganych danych!";
        } else if((notify == true) && (mail.equals(""))){
            return "Error! Brak wymaganych danych!";
        } else {            
            CommentForm cf = new CommentForm();
            cf.setArticleId(idArticle);
            cf.setMail(mail);
            cf.setName(name);
            cf.setNotify(notify);
            cf.setText(text);
            this.articleService.addComment(cf);
            return "ok";
        }        
    }
    
    @RequestMapping(value = "/rest/comcomment", method = RequestMethod.POST)
    public @ResponseBody String commentComment(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        int idArticle = Integer.parseInt(request.getParameter("idArticle"));
        int idComment = Integer.parseInt(request.getParameter("idComment"));
        
        if((name.equals("") || (text.equals("")) || (idArticle == 0) || (idComment == 0))){
            return "Error! Brak wymaganych danych!";
        } else {
            this.articleService.setCommentToComment(name, text, idComment, idArticle);
            return "ok";
        }        
    }
    
    @RequestMapping(value = "/rest/last", method = RequestMethod.GET)
    public @ResponseBody List<SimpleUrlData> getLastArticle(HttpServletRequest request, HttpServletResponse response){        
        response.setContentType("application/json;charset=UTF-8");
        return this.articleService.getLastArticle(5);
    }
    
    private String prepareJsonString(String tekst){          
        StringBuilder tmp = new StringBuilder(tekst);
        
        int changes = 0;
        for (int i = 0; i < tekst.length(); i++) {
            switch (tekst.charAt(i)) {
                case '"' : // "
                case '\u005C\u005C' : //
                case '/' : // /
                case '\b'://'\u0008' : // b backspace
                case '\f'://'\u000C' : // f formfeed
                case '\n' : // n newline
                case '\r'://'\u240D' : // r carrige return
                case '\t'://'\u2B7E' : // t horizontal tab
                    tmp.insert(i + changes++, '\u005C\u005C');
                    break;
                default:                    
                    break;
            }
        } 
        String result = tmp.toString();
        System.out.println(result);
        return result;
    }
    
    private String createJSONfromArticle(Article article){
        article.setContent(prepareJsonString(article.getContent()));
        System.out.println(article.getContent());
        String result = "{\"title\":\"" + article.getTitle() + "\"," +
                "\"author\":\"" + article.getAuthor()+ "\"," + 
                "\"content\":\"" + article.getContent()+ "\"," +
                "\"createDate\":\"" + article.getCreateDate()+ "\"," + 
                "\"modifyDate\":\"" + article.getModifyDate()+ "\"," + 
                "\"source\":\"" + article.getSource()+ "\"," + 
                "\"category\":\"" + article.getCategory().getName()+ "\"," + 
                "\"id\":" + article.getId()+ ",\"tags\":[";
        for (Tag tag : article.getTags()) {
            result += "\"" + tag.getName() + "\",";
        }
        result = result.substring(0, result.length() - 1) + "],\"comments1\":[";
        String tmp = "";
        for (Comment comment : article.getComments()) {
            comment.setText(prepareJsonString(comment.getText()));
            tmp += "{\"id\":" + comment.getId() + "," +
                    "\"name\":\"" + comment.getName()+ "\"," +
                    "\"text\":\"" + comment.getText()+ "\"," +
                    "\"mail\":\"" + comment.getMail()+ "\"," +
                    "\"createDate\":\"" + comment.getCreateDate()+ "\"," +
                    "\"notify\":" + comment.isNotify()+ "," +
                    "\"comments2\":[";
            String t = "";
            for (Comment c : comment.getComment()) {
                c.setText(prepareJsonString(c.getText()));
                t += "{\"id\":" + c.getId() + "," +
                    "\"name\":\"" + c.getName()+ "\"," +
                    "\"text\":\"" + c.getText()+ "\"," +
                    "\"createDate\":\"" + c.getCreateDate()+ "\"},";
            }
            if(!t.equals(""))
                t = t.substring(0, t.length() - 1);
            tmp += t + "]},";
        }
        if(!tmp.equals(""))
            tmp = tmp.substring(0, tmp.length() - 1);        
        return result + tmp + "]}";
    }
}
