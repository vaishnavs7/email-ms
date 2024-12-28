package com.synccreativelabs.email_svc.controller;

import com.synccreativelabs.email_svc.repository.EmailTemplateRepository;
import com.synccreativelabs.email_svc.model.request.EmailTemplateRequest;
import com.synccreativelabs.email_svc.service.EmailTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
@AllArgsConstructor
public class EmailTemplateController {

    @Autowired
    private EmailTemplateRepository templateRepository;
    private final EmailTemplateService emailTemplateService;

    @PostMapping
    public String saveTemplate(@RequestBody EmailTemplateRequest template) {
        return emailTemplateService.createTemplate(template);
    }
}
