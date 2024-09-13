package infnet.edu.emailservice.Domain.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import infnet.edu.emailservice.Contracts.SendEmailRequest;
import infnet.edu.emailservice.Domain.Primitives.EntityRoot;
import infnet.edu.emailservice.Domain.ValueObject.EmailAddress;

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
    
    public static EmailObject MapJsonMessageToEmailObject(String message) throws Exception
    {

        String emailAddressFieldName = "emailAddress";
        String subjectFieldName = "subject";
        String contentFieldName = "content";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode tree = mapper.readTree(message);

        EmailObject result = new EmailObject(
            0,
            EmailAddress.Create(tree.get(emailAddressFieldName).asText()).Value(),
            tree.get(subjectFieldName).asText(),
            tree.get(contentFieldName).asText()
        );

        return result;
    }

    public boolean ValidateObject(EmailObject obj)
    {
        if(subject.length() == 0 || content.length() == 0 )
            return false;
        return true;
    }
}

