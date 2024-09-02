package infnet.edu.emailservice.Domain.Models;

import java.time.LocalDateTime;

import infnet.edu.emailservice.Domain.Primitives.EntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Email")
public class EmailDTO extends EntityRoot{

    @Column
    public String emailAddress;
    @Column
    public String subject;
    @Column
    public String content;
    @Column
    public LocalDateTime date_sent;
    
    public EmailDTO() {    
    }
    
    public EmailDTO(long id, String emailAddress, String subject, String content, LocalDateTime date_sent) {
        super(id);
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.content = content;
        this.date_sent = date_sent;
    }
    
   

}
