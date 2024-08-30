package infnet.edu.emailservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.emailservice.Contracts.SendEmailRequest;
import infnet.edu.emailservice.Domain.Errors.EmailDomainErrors;
import infnet.edu.emailservice.Domain.Models.EmailObject;
import infnet.edu.emailservice.Domain.Shared.Result;
import infnet.edu.emailservice.Infrastructure.EmailRepository;
import infnet.edu.emailservice.Infrastructure.EmailService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EmailSenderController {

    private final String Base = "api/Email/";
    private final String PostEndpoint = Base + "send";

    @Autowired
    private EmailService _Service;

    @Autowired
    private EmailRepository _Repository;

    @PostMapping(PostEndpoint)
    public ResponseEntity<Result<?>> SendEmail(@RequestBody SendEmailRequest request,
            HttpServletRequest httpRequest)
            throws Exception {
        try {
            EmailObject obj = EmailObject.MapRequestToEmailObject(request);

            _Service.sendSimpleEmail(obj);

            _Repository.save(EmailObject.MapRequestToEmailDto(obj));

            return new ResponseEntity<Result<?>>(
                    Result.<EmailObject>Success(obj),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Result<?>>(Result.Failure(
                    EmailDomainErrors.Empty()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
