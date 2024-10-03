package infnet.edu.emailservice.Infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import infnet.edu.emailservice.Domain.Models.EmailDTO;
import infnet.edu.emailservice.Domain.Models.EmailObject;

@Service
public class EmailService {

    private static final String StandardEmailSenderAddress = "marco.gamedev.rg340@gmail.com";
    private static final String MailSendMsg = "Mail Send...";

    @Autowired
    private JavaMailSender _MailSender;

    @Autowired
    private EmailRepository _EmailRepository;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = CreateEmailMessage(toEmail, subject, body);

        _MailSender.send(message);

        System.out.println(MailSendMsg);
    }

    public void sendSimpleEmail(EmailObject obj) throws Exception {

        if (obj.ValidateObject(obj)) {
            SimpleMailMessage message = CreateEmailMessage(obj.emailAddress.value, obj.subject, obj.content);

            _MailSender.send(message);

            EmailDTO dto = EmailObject.MapRequestToEmailDto(obj);

            _EmailRepository.save(dto);

            System.out.println(MailSendMsg);
        }

    }

    public List<EmailObject> GeEmailsByEmail(String emailAddress) throws Exception {
        var emails = _EmailRepository.GetEmailsByEmail(emailAddress);

        List<EmailObject> result = new ArrayList<EmailObject>();      

        result = EmailDTO.MapRequestToEmailObject(emails);

        return result;
    }

    public void DeleteEmails(String emailAddress) throws Exception
    {
         _EmailRepository.deleteByEmail(emailAddress);
    }

    private SimpleMailMessage CreateEmailMessage(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(StandardEmailSenderAddress);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        return message;
    }

}
