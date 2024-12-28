package com.synccreativelabs.email_svc.controller;


import com.synccreativelabs.email_svc.model.request.EmailRequest;
import com.synccreativelabs.email_svc.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String templateName,
            @RequestBody EmailRequest request) throws MessagingException {
        emailService.sendEmail(templateName, request.getEmails(), request.getPlaceholders());
        return "Emails sent successfully!";
    }
}


