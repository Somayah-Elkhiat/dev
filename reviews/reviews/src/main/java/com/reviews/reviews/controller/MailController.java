package com.reviews.reviews.controller;

import com.reviews.reviews.model.ReviewRequestModel;
import com.reviews.reviews.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/send-text")
    public String send(List<ReviewRequestModel> data) throws IOException {
        System.out.println(data.get(0).getEmail());
        return mailService.sendTextEmail(data.get(0).getEmail(),data.get(0).getSku());
    }
}
