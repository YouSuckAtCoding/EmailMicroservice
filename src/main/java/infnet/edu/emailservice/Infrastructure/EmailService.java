package infnet.edu.emailservice.Infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import infnet.edu.emailservice.Domain.Models.EmailObject;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender _MailSender;

    public void sendSimpleEmail(String toEmail,String subject,String body) 
    {        
        SimpleMailMessage message = CreateEmailMessage(toEmail, subject, body);
        
        _MailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendSimpleEmail(EmailObject obj) {
        
        SimpleMailMessage message = CreateEmailMessage(obj.emailAddress.value, obj.subject, obj.content);

        _MailSender.send(message);

        System.out.println("Mail Send...");
    }
    
    private SimpleMailMessage CreateEmailMessage(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("marco.gamedev.rg340@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        return message;
    }

}
