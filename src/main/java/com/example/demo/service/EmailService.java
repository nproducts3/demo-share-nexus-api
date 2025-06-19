package com.example.demo.service;

import com.example.demo.entity.DemoSession;
import com.example.demo.entity.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.from}")
    private String fromEmail;
    
    /**
     * Send notification email when a demo session is created
     * 
     * @param session The created demo session
     * @param createdByUser The user who created the session
     * @param recipients List of users to notify
     */
    public void sendSessionCreationNotification(DemoSession session, UserManagement createdByUser, List<UserManagement> recipients) {
        try {
            for (UserManagement recipient : recipients) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(recipient.getEmail());
                message.setSubject("New Demo Session: " + session.getTitle());
                message.setText(createSessionCreationEmailContent(session, createdByUser, recipient));
                
                mailSender.send(message);
                logger.info("Session creation notification sent to: {}", recipient.getEmail());
            }
        } catch (Exception e) {
            logger.error("Failed to send session creation notification emails", e);
        }
    }
    
    /**
     * Send notification email to session creator
     * 
     * @param session The created demo session
     * @param createdByUser The user who created the session
     */
    public void sendSessionCreationConfirmation(DemoSession session, UserManagement createdByUser) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(createdByUser.getEmail());
            message.setSubject("Demo Session Created Successfully: " + session.getTitle());
            message.setText(createSessionConfirmationEmailContent(session, createdByUser));
            
            mailSender.send(message);
            logger.info("Session creation confirmation sent to creator: {}", createdByUser.getEmail());
        } catch (Exception e) {
            logger.error("Failed to send session creation confirmation email", e);
        }
    }
    
    /**
     * Create email content for session creation notification
     */
    private String createSessionCreationEmailContent(DemoSession session, UserManagement createdByUser, UserManagement recipient) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return String.format(
            "Hello %s,\n\n" +
            "A new demo session has been created and you are invited to attend!\n\n" +
            "Session Details:\n" +
            "• Title: %s\n" +
            "• Technology: %s\n" +
            "• Date: %s\n" +
            "• Time: %s\n" +
            "• Location: %s\n" +
            "• Difficulty: %s\n" +
            "• Duration: %d minutes\n" +
            "• Created by: %s\n\n" +
            "Description:\n%s\n\n" +
            "Please mark your calendar and prepare for this session.\n\n" +
            "Best regards,\n" +
            "Demo Session Management System",
            
            recipient.getName(),
            session.getTitle(),
            session.getTechnology(),
            session.getDate().format(dateFormatter),
            session.getTime().format(timeFormatter),
            session.getLocation(),
            session.getDifficulty(),
            session.getDuration(),
            createdByUser.getName(),
            session.getDescription()
        );
    }
    
    /**
     * Create email content for session creation confirmation
     */
    private String createSessionConfirmationEmailContent(DemoSession session, UserManagement createdByUser) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return String.format(
            "Hello %s,\n\n" +
            "Your demo session has been created successfully!\n\n" +
            "Session Details:\n" +
            "• Title: %s\n" +
            "• Technology: %s\n" +
            "• Date: %s\n" +
            "• Time: %s\n" +
            "• Location: %s\n" +
            "• Difficulty: %s\n" +
            "• Duration: %d minutes\n" +
            "• Max Attendees: %d\n" +
            "• Session ID: %s\n\n" +
            "Description:\n%s\n\n" +
            "The session has been scheduled and notifications have been sent to all participants.\n\n" +
            "Best regards,\n" +
            "Demo Session Management System",
            
            createdByUser.getName(),
            session.getTitle(),
            session.getTechnology(),
            session.getDate().format(dateFormatter),
            session.getTime().format(timeFormatter),
            session.getLocation(),
            session.getDifficulty(),
            session.getDuration(),
            session.getMaxAttendees(),
            session.getId(),
            session.getDescription()
        );
    }
} 