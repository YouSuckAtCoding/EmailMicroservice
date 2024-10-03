package infnet.edu.emailservice.Infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infnet.edu.emailservice.Domain.Models.EmailDTO;
import jakarta.transaction.Transactional;

@Repository
public interface EmailRepository extends JpaRepository<EmailDTO, Long>{

    @Query(nativeQuery = true, value = "Select * from email where email_address = ?1")
    public List<EmailDTO> GetEmailsByEmail(String emailAddress);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "Delete from email where email_Address = ?1")
    public void deleteByEmail(String emailAddress);

}   
