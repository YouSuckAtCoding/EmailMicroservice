package infnet.edu.emailservice.Domain.Models;

import java.time.LocalDateTime;

import infnet.edu.emailservice.Contracts.SendEmailRequest;
import infnet.edu.emailservice.Primitives.EntityRoot;
import infnet.edu.emailservice.ValueObject.EmailAddress;

public class EmailObject extends EntityRoot
{
    public EmailAddress emailAddress;
    public String subject;
    public String content;

    public EmailObject(){
    }

    public EmailObject(long id, EmailAddress emailAddress, String subject, String content) {
        super(id);
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.content = content;
    }

    public static EmailObject MapRequestToEmailObject(SendEmailRequest request) throws Exception
    {
        return new EmailObject(0, 
        EmailAddress.Create(request.emailAddress).Value(),
        request.subject, 
        request.content);
    }

    public static EmailDTO MapRequestToEmailDto(EmailObject request) throws Exception
    {
        return new EmailDTO(0, 
        request.emailAddress.value,
        request.subject, 
        request.content,
        LocalDateTime.now()
        );
    }

}

