package com.synccreativelabs.email_svc.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailRequest {
    private List<String> emails;
    private Map<String, Object> placeholders;

    // Getters and Setters
}