package infnet.edu.emailservice.Domain;

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

}
