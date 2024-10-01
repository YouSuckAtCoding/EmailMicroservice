package infnet.edu.emailservice.Infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infnet.edu.emailservice.Domain.Models.EmailDTO;

@Repository
public interface EmailRepository extends JpaRepository<EmailDTO, Long>{

    @Query(nativeQuery = true, value = "Select * from email where email_Address = @emailAddress")
    public List<EmailDTO> GetEmailsByEmail(String emailAddress);

    @Query(nativeQuery = true, value = "Delete from email where email_Address = @emailAddress")
    public void deleteByEmail(String emailAddress);

}   
