package com.controllers;

import com.models.*;
import com.services.interfaces.ArticleServiceInterface;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/artykul")
public class ArticleController {
    
    @Autowired(required=true)
    @Qualifier(value="as")
    private ArticleServiceInterface articleService;
    
    
    @RequestMapping("/nowy")
    public String getArticleForm(Model model) {
        model.addAttribute("categoryList", this.articleService.getAllCategory());
        model.addAttribute("articleForm", new ArticleForm());
        return "new_article"; 
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields( new String[] {"id", "title", "category", "content", "tags", "source", "name", "mail", "notify", "text", "articleId", "searchText"});

        // Converts empty string to null, which is nice since most validation rules fire only if the field isn't null.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/dodaj", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("articleForm") @Valid ArticleForm form, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("categoryList", this.articleService.getAllCategory());
            model.addAttribute("articleForm", form);
            return "new_article";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            int id = this.articleService.create(form, auth.getName());
            return "redirect:/artykul/" + id;
        } 
    }
    
    @RequestMapping(value = "/{article}", method = RequestMethod.GET)
    public String getFileUser(@PathVariable int article, Model model) {
        Map<String, SimpleUrlData> tmp = this.articleService.getPrevAndNext(article);

        model.addAttribute("prev", tmp.get("prev"));
        model.addAttribute("next", tmp.get("next"));
        model.addAttribute("commentForm", new CommentForm(article));
        model.addAttribute("article", this.articleService.get(article));
        return "article";
    }

    @RequestMapping(value = "/komentarz/dodaj", method = RequestMethod.POST)
    public String addCommentToArticle(@ModelAttribute("commentForm") @Valid CommentForm commentForm, BindingResult result, Model model){
        if((commentForm.isNotify()) && (commentForm.getMail() == null)){
            result.rejectValue("notify", "message.not_mail", "Do powiadomieĹ„ potrzebny jest adres e-mail!");
        }
                
        if(result.hasErrors()){
            model.addAttribute("commentForm", commentForm);
            model.addAttribute("article", this.articleService.get(commentForm.getArticleId()));
            return "article";
        } else {
            this.articleService.addComment(commentForm);
            return "redirect:/artykul/" + commentForm.getArticleId(); 
        }
    }
    
    @RequestMapping(value = "/komentarz/komentarz/dodaj", method = RequestMethod.POST) 
    public String addCommentToComment(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        int idArticle = Integer.parseInt(request.getParameter("idArticle"));
        int idComment = Integer.parseInt(request.getParameter("idComment"));
        
        this.articleService.setCommentToComment(name, text, idComment, idArticle);
        return "redirect:/artykul/" + idArticle; 
    }
    
    @RequestMapping(value = "/edycja/{article}", method = RequestMethod.GET)
    public String editArticle(@PathVariable int article, Model model) {
        model.addAttribute("categoryList", this.articleService.getAllCategory());
        model.addAttribute("articleForm", this.articleService.getArticleToEdit(article));
        return "new_article";
    }
    
    @RequestMapping(value = "/ukryj/{article}", method = RequestMethod.GET)
    public String hideArticle(@PathVariable int article) {        
        this.articleService.setVisible(article, false);
        return "redirect:/artykul/" + article;
    }
    
    @RequestMapping(value = "/pokaz/{article}", method = RequestMethod.GET)
    public String showArticle(@PathVariable int article) {        
        this.articleService.setVisible(article, true);
        return "redirect:/artykul/" + article;
    }
    
    @RequestMapping(value = "/szukaj", method = RequestMethod.GET)
    public String search(HttpServletRequest request, Model model){
        String text = request.getParameter("searchText");
        
        model.addAttribute("searchText", text);
        model.addAttribute("searchResult", this.articleService.getArticleByFullText(text));
        return "search_result";
    }
}  