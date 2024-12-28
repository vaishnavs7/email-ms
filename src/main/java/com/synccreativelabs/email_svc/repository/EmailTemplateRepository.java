package com.synccreativelabs.email_svc.repository;

import com.synccreativelabs.email_svc.model.entity.EmailTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, String> {
    EmailTemplate findByName(String name);
}
