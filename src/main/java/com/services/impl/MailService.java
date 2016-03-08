package com.services.impl;

import com.models.Comment;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service("mailService")
public class MailService {
    
    @Autowired
    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void doMail(Comment comment, int idAarticle){
        String subject = "MHelper - dostałeś odpowiedź!";
        String msg = "Witamy,\n"
                + "otrzymałeś/aś tego maila, ponieważ zaznaczyłeś/aś, że chcesz otrzymywać powiadomienia"
                + " jeśli ktoś skomentuje Twój komentarz w serwisie MHelper.\n"
                + "Tak się właśnie stało! \n"
                + "http://78.138.100.222:8080/MHelper/artykul/" + idAarticle
                + "\n\n---\nPozdrawiamy";
        sendMail("MHelper", comment.getMail(), subject, msg); 
    }
    
    public void sendMail(String from, String to, String subject, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);
        
        mailSender.send(simpleMailMessage);
    }
}
