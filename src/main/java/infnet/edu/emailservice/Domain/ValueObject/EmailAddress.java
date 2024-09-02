package infnet.edu.emailservice.Domain.ValueObject;

import java.util.List;

import infnet.edu.emailservice.Domain.Errors.EmailDomainErrors;
import infnet.edu.emailservice.Domain.Primitives.ValueObject;
import infnet.edu.emailservice.Domain.Shared.Result;

public class EmailAddress extends ValueObject{

    public String value;

    private EmailAddress(String value)
    {
        this.value = value;
    }

    public static Result<EmailAddress> Create(String value) throws Exception
    {
        if(value.length() == 0)
        {
            return Result.<EmailAddress>Failure(EmailDomainErrors.Empty());
        }
        
        return Result.<EmailAddress>Success(new EmailAddress(value)); 
    }

    @Override
    public List<Object> getAtomicValues() {
        return List.of(value);
    }

}
