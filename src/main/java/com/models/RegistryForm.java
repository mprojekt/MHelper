package com.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;


public class RegistryForm {
    
    @Size(min = 0, max = 30, message = "Imię musi mieć maks. 30 znaków!")
    private String name;
    
    @NotNull(message = "Login nie może być pusty!")
    @Size(min = 3, max = 20, message = "Login musi mieć min 3, a maks. 20 znaków!")
    private String login;
    
    @NotNull(message = "Hasło nie może być puste!")
    @Size(min = 5, max = 20, message = "Hasło musi mieć min 5, a maks. 20 znaków!")
    private String password;    
    private String passwordConfirm;
    
    @NotNull(message = "E-mail nie może być pusty!")
    @Size(min = 5, max = 50, message = "E-mail musi mieć min 5, a maks. 50 znaków!")
    @Email
    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
}
