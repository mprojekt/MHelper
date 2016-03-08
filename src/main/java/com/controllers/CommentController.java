package com.controllers;

import com.models.CommentForm;
import com.services.interfaces.ArticleServiceInterface;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {
    
    @Autowired(required=true)
    @Qualifier(value="as")
    private ArticleServiceInterface articleService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields( new String[] { "name", "mail", "notify", "tex", "articleId"});

        // Converts empty string to null, which is nice since most validation rules fire only if the field isn't null.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    
}
