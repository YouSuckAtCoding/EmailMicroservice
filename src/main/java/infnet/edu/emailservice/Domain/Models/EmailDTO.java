package infnet.edu.emailservice.Domain.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import infnet.edu.emailservice.Domain.Primitives.EntityRoot;
import infnet.edu.emailservice.Domain.ValueObject.EmailAddress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Email")
public class EmailDTO extends EntityRoot {

    @Column
    public String emailAddress;
    @Column
    public String subject;
    @Column
    public String content;
    @Column
    public LocalDateTime date_sent;
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(LocalDateTime date_sent) {
        this.date_sent = date_sent;
    }

    public EmailDTO() {
    }

    public EmailDTO(long id, String emailAddress, String subject, String content, LocalDateTime date_sent) {
        super(id);
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.content = content;
        this.date_sent = date_sent;
    }

    public static List<EmailObject> MapRequestToEmailObject(List<EmailDTO> request) throws Exception
    {
        List<EmailObject> res = new ArrayList<EmailObject>();
        for (EmailDTO email : request) {
            
            var obj =  new EmailObject(0, 
            EmailAddress.Create(email.emailAddress).Value(),
            email.subject, 
            email.content);

            res.add(obj);
        }

        return res;
    }

}
