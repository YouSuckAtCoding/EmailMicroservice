package infnet.edu.emailservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import infnet.edu.emailservice.Domain.Models.EmailObject;
import infnet.edu.emailservice.ValueObject.EmailAddress;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class EmailObjectTests {

    @Test
    void Should_Create_EmailAddress_And_Return_Its_Value() throws Exception {

        String email = "marco@gmail.com";
        var res = EmailAddress.Create(email);

        Assert.isTrue(res.Value().value == email, "Correct");
    }

    @Test
    void Should_Not_Create_EmailAddress_And_Return_Failure_Result() throws Exception {

        String email = "";
        var res = EmailAddress.Create(email);

        Assert.isTrue(res.IsSuccess == false, "Failure");
    }

    @Test
    void Should_Create_EmailObject_And_Return_Its_Value() throws Exception{

        String email = "marco@gmail.com";
        long id = 1;
        EmailAddress emailaAddress = EmailAddress.Create(email).Value();

        var obj = new EmailObject(id, emailaAddress, "teste", "teste");

        Assert.isTrue((obj.emailAddress == emailaAddress && obj.getClass() == EmailObject.class), "Created");

    }
}
