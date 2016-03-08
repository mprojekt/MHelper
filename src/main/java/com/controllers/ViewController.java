package com.controllers;

import com.models.*;
import com.services.interfaces.ArticleServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    
    @Autowired(required=true)
    @Qualifier(value="as")
    private ArticleServiceInterface articleService;
    	
    @RequestMapping("/")
    public String start() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) { 
        model.addAttribute("lastArticle", this.articleService.getLastArticle(5));
        return "home";
    }
    
    @RequestMapping("/login")
    public String getLoginForm() {
        return "login"; 
    }
    
    @RequestMapping("/login_failed")
    public String isLoginError(Model model) {
        model.addAttribute("loginError", "true");
        return "login"; 
    }
    
    @RequestMapping("/rejestracja")
    public String getRegistryForm(Model model) {
        model.addAttribute("registryForm", new RegistryForm());
        return "registry"; 
    }
    
    @RequestMapping("/spis_tresci")
    public String getTableOfContent(Model model) {
        model.addAttribute("toc", this.articleService.getToc());
        model.addAttribute("hiden", this.articleService.getUnvisible());
        return "toc"; 
    }
    
    @RequestMapping("/spis_tagow")
    public String getTableOfTags(Model model) {
        model.addAttribute("tags", this.articleService.getTot());
        return "tot"; 
    }
    
    @RequestMapping("/mobile")
    public String getMobile(Model model) {
        model.addAttribute("actual", this.articleService.get(6));
        return "mobile"; 
    }
}