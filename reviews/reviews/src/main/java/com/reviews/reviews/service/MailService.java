package com.reviews.reviews.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;
    public String sendTextEmail(String email, List<String> skus) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("s.alkhiat@boutiqaat.com");
        String subject = "Order Completed";
        Email to = new Email(email);
        Content content = new Content("text/plain", "Thank you for shopping with us, this is your order: " + skus.toString() );
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
