package infnet.edu.emailservice.Domain.Errors;

import infnet.edu.emailservice.Domain.Shared.Error;

public class EmailDomainErrors {

    public static final Error Empty (){
        
        return new Error("Email.Empty", "EmailAddress is empty");
    } 


}
