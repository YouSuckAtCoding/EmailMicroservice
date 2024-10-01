package infnet.edu.emailservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final String GetByEmailEndpoint = Base + "getEmails";
    private final String DeleteEmails = Base + "delete";

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

    @GetMapping(GetByEmailEndpoint)
    public ResponseEntity<Result<?>> GetEmailsByEmail(@RequestParam String emailAddress) throws Exception {
        try {
            return new ResponseEntity<>(Result.<List<EmailObject>>Success(_Service.GeEmailsByEmail(emailAddress)),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Result<?>>(Result.Failure(
                    EmailDomainErrors.Empty()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping(DeleteEmails)
    public ResponseEntity<?> DeleteEmails(@RequestParam String emailAddress) throws Exception {
        try {
            _Service.DeleteEmails(emailAddress);
            return new ResponseEntity("Ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Result<?>>(Result.Failure(
                    EmailDomainErrors.Empty()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
