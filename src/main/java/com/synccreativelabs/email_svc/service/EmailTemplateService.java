package com.synccreativelabs.email_svc.service;

import com.synccreativelabs.email_svc.model.entity.EmailTemplate;
import com.synccreativelabs.email_svc.repository.EmailTemplateRepository;
import com.synccreativelabs.email_svc.model.request.EmailTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;


    public String createTemplate(EmailTemplateRequest emailTemplateRequest){
        // Validate input
        if (emailTemplateRequest.getName() == null || emailTemplateRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Template name is required.");
        }
        if (emailTemplateRequest.getSubject() == null || emailTemplateRequest.getSubject().isEmpty()) {
            throw new IllegalArgumentException("Template subject is required.");
        }
        if (emailTemplateRequest.getBody() == null || emailTemplateRequest.getBody().isEmpty()) {
            throw new IllegalArgumentException("Template body is required.");
        }
        EmailTemplate template = new EmailTemplate();
        template.setBody(emailTemplateRequest.getBody());
        template.setName(emailTemplateRequest.getName());
        template.setSubject(emailTemplateRequest.getSubject());
        template.setTemplateId(RandomStringUtils.randomAlphanumeric(16).toUpperCase());

        // Save template to database
        emailTemplateRepository.save(template);
        return template.getTemplateId();
    }
}
