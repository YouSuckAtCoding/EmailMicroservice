package infnet.edu.emailservice.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infnet.edu.emailservice.Domain.Models.EmailDTO;

@Repository
public interface EmailRepository extends JpaRepository<EmailDTO, Long>{

}
