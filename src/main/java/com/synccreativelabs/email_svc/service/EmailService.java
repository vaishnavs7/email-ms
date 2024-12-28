package com.synccreativelabs.email_svc.service;

import com.synccreativelabs.email_svc.model.entity.EmailTemplate;
import com.synccreativelabs.email_svc.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EmailService {

    @Autowired
    private EmailTemplateRepository templateRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(String templateName, List<String> emails, Map<String, Object> placeholders) throws MessagingException {
        EmailTemplate template = templateRepository.findByName(templateName);
        if (template == null) {
            throw new RuntimeException("Template not found: " + templateName);
        }

        System.out.println("Template Body: " + template.getBody());
        System.out.println("Placeholders: " + placeholders);

        Context context = new Context();
        context.setVariables(placeholders);


        // Check for any errors in template processing
        String processedHtml;
        AtomicReference<String> finalContent = new AtomicReference<>();
        AtomicReference<String> finalSubject = new AtomicReference<>();

        try {
            processedHtml = templateEngine.process(template.getBody(), context);
            finalContent.set(processedHtml);
            finalSubject.set(template.getSubject());

            placeholders.keySet().forEach(ks -> {
                finalSubject.set(finalSubject.get().replace("${" + ks + "}", placeholders.get(ks).toString()));
                finalContent.set(finalContent.get().replace("${" + ks + "}", placeholders.get(ks).toString()));
            });
        } catch (Exception e) {
            throw new RuntimeException("Error processing template: " + e.getMessage(), e);
        }
        System.out.println(finalContent);

        for (String email : emails) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject(finalSubject.get());
            helper.setText(finalContent.get(), true);

            mailSender.send(message);
            String template1 = "<html><body><h1>Welcome, ${name}!</h1><p>We are glad to have you on board.</p></body></html>";
            Context context1 = new Context();
            context.setVariable("name", "John Doe");

            TemplateEngine engine = new TemplateEngine();
            engine.setTemplateResolver(new StringTemplateResolver());
            String output = engine.process(template1, context1);
            System.out.println(output); // Should print: <html><body><h1>Welcome, John Doe!</h1><p>We are glad to have you on board.</p></body></html>

        }
    }

}
