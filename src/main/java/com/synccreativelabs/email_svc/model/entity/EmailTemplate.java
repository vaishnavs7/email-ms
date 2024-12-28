package com.synccreativelabs.email_svc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "templates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    @Id
    private String id;
    private String name; // Template name
    private String subject;
    @Indexed(unique = true)
    private String templateId;
    private String body; // HTML body with placeholders

    // Getters and Setters
}
