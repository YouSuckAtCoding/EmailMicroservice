package infnet.edu.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.emailservice.Contracts.SendEmailRequest;
import infnet.edu.emailservice.Domain.Models.EmailObject;
import infnet.edu.emailservice.Domain.Shared.Error;
import infnet.edu.emailservice.Domain.Shared.Result;
import infnet.edu.emailservice.Infrastructure.EmailService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EmailSenderController {

    private final String Base = "api/Email/";
    private final String PostEndpoint = Base + "send";

    @Autowired
    private EmailService _Service;

    @PostMapping(PostEndpoint)
    public ResponseEntity<Result<?>> SendEmail(@RequestBody SendEmailRequest request,
            HttpServletRequest httpRequest)
            throws Exception {
        try {
            EmailObject obj = EmailObject.MapRequestToEmailObject(request);

            _Service.sendSimpleEmail(obj);

            return new ResponseEntity<Result<?>>(
                    Result.<EmailObject>Success(obj),
                    HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            return new ResponseEntity<Result<?>>(Result.Failure(
                    new Error(e.getMessage(), e.getLocalizedMessage())),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
