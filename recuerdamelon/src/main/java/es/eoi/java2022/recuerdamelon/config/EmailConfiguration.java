package es.eoi.java2022.recuerdamelon.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = { "es.eoi.java2022.recuerdamelon.service" })
@PropertySource(value={"classpath:application.yaml"})
public class EmailConfiguration {

    @Value("${spring.mail.host}")
    private String mailServerHost;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String mailServerUsername;

    @Value("${spring.mail.password}")
    private String mailServerPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailServerAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailServerStartTls;

    @Bean
    public JavaMailSender getEmailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailServerHost);
        mailSender.setPort(mailServerPort);

        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", mailServerAuth);
        props.put("mail.smtp.starttls.enable", mailServerStartTls);
        props.put("mail.debug", "true");

        return mailSender;
    }

//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("This is the test email template for your email:\n%s\n");
//        return message;
//    }

    //Template los emails
//     @Bean
//     public SpringTemplateEngine thymeleafTemplateEngine(ITemplateResolver templateResolver) {
//         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//         templateEngine.setTemplateResolver(templateResolver);
//         templateEngine.setTemplateEngineMessageSource(emailMessageSource());
//         return templateEngine;
//     }


     @Bean
     public ResourceBundleMessageSource emailMessageSource() {
         final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
         messageSource.setBasename("mailMessages");
         return messageSource;
     }
}
