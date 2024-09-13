package infnet.edu.emailservice.Messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import infnet.edu.emailservice.Domain.Models.EmailObject;
import infnet.edu.emailservice.Infrastructure.EmailService;

@Component
public class QueueConsumer {

    @Autowired
    private EmailService _Service;

    @RabbitListener(queues = "${queue.name}")
    public void reciever(@Payload String fileBody) throws Exception
    {
        EmailObject recieved = EmailObject.MapJsonMessageToEmailObject(fileBody);
        
        _Service.sendSimpleEmail(recieved);
    }
}
