package com.controllers;

import com.models.RegistryForm;
import com.services.interfaces.AcountServiceInterface;
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
public class AcountController {
    
    @Autowired(required=true)
    @Qualifier(value="acountService")
    private AcountServiceInterface acountService;
     
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields( new String[] {"name", "login", "password", "passwordConfirm", "mail"});

        // Converts empty string to null, which is nice since most validation rules fire only if the field isn't null.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @RequestMapping(value = "/uzytkownik/dodaj", method = RequestMethod.POST)
    public String registryNewUser(@ModelAttribute("registryForm") @Valid RegistryForm form, BindingResult result, Model model) {
        if(form.getPassword() != null)
            if((!form.getPassword().equals(form.getPasswordConfirm())) && (!result.hasFieldErrors("password")))
                result.rejectValue("passwordAgain", "message.duplicate", "Hasła nie były identyczne!");
        
        if(this.acountService.existLogin(form.getLogin()))
            result.rejectValue("login", "message.exist", "Login jest już zajęty");
        
        if(result.hasErrors()){
            model.addAttribute("registryForm", form);
            return "registry";
        } else {
            this.acountService.create(form);
            return "redirect:/login";
        }        
    }

}
