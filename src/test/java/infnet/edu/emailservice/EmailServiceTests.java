package infnet.edu.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import infnet.edu.emailservice.Domain.Models.EmailObject;
import infnet.edu.emailservice.Domain.ValueObject.EmailAddress;
import infnet.edu.emailservice.Infrastructure.EmailService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService _Service;
    @Test
    void Should_Send_Email_To_Specified_Address()
    {
        String email = "marco.aferreira@al.infnet.edu.br";
        String subject = "Teste123";
        String content = "This is the content of the test email";

        _Service.sendSimpleEmail(email, subject, content);


    }

    @Test
    void Should_Send_Email_From_EmailObject_To_Specified_Address() throws Exception
    {
        String email = "marco.aferreira@al.infnet.edu.br";
        String subject = "Teste123";
        String content = "This is the content of the test email";

        EmailObject object = new EmailObject(0, EmailAddress.Create(email).Value(), subject, content);

        _Service.sendSimpleEmail(object);


    }

    @Test
    void SHould_Retrieve_Emails_By_Email() throws Exception
    {
        String emailAddress = "marco.aferreira@al.infnet.edu.br";

        var result = _Service.GeEmailsByEmail(emailAddress);

        Assert.isTrue(result.getClass() == new ArrayList<EmailObject>().getClass(), "Ok");
    }

}
