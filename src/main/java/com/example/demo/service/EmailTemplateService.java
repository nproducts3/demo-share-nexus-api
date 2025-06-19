package com.example.demo.service;

import com.example.demo.entity.DemoSession;
import com.example.demo.entity.UserManagement;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailTemplateService {
    
    /**
     * Create HTML email content for session creation notification
     */
    public String createSessionCreationHtmlEmail(DemoSession session, UserManagement createdByUser, UserManagement recipient) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>New Demo Session Invitation</title>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #007bff; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }
                    .content { background-color: #f8f9fa; padding: 20px; border-radius: 0 0 5px 5px; }
                    .session-details { background-color: white; padding: 15px; margin: 15px 0; border-radius: 5px; border-left: 4px solid #007bff; }
                    .footer { text-align: center; margin-top: 20px; color: #666; font-size: 12px; }
                    .highlight { color: #007bff; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>🎯 New Demo Session Invitation</h1>
                    </div>
                    <div class="content">
                        <p>Hello <span class="highlight">%s</span>,</p>
                        
                        <p>A new demo session has been created and you are invited to attend!</p>
                        
                        <div class="session-details">
                            <h3>📋 Session Details</h3>
                            <p><strong>Title:</strong> %s</p>
                            <p><strong>Technology:</strong> %s</p>
                            <p><strong>Date:</strong> %s</p>
                            <p><strong>Time:</strong> %s</p>
                            <p><strong>Location:</strong> %s</p>
                            <p><strong>Difficulty:</strong> %s</p>
                            <p><strong>Duration:</strong> %d minutes</p>
                            <p><strong>Created by:</strong> %s</p>
                        </div>
                        
                        <div class="session-details">
                            <h3>📝 Description</h3>
                            <p>%s</p>
                        </div>
                        
                        <p>Please mark your calendar and prepare for this session.</p>
                        
                        <p>Best regards,<br>
                        <strong>Demo Session Management System</strong></p>
                    </div>
                    <div class="footer">
                        <p>This is an automated notification. Please do not reply to this email.</p>
                    </div>
                </div>
            </body>
            </html>
            """,
            
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
     * Create HTML email content for session creation confirmation
     */
    public String createSessionConfirmationHtmlEmail(DemoSession session, UserManagement createdByUser) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Demo Session Created Successfully</title>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #28a745; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }
                    .content { background-color: #f8f9fa; padding: 20px; border-radius: 0 0 5px 5px; }
                    .session-details { background-color: white; padding: 15px; margin: 15px 0; border-radius: 5px; border-left: 4px solid #28a745; }
                    .footer { text-align: center; margin-top: 20px; color: #666; font-size: 12px; }
                    .highlight { color: #28a745; font-weight: bold; }
                    .success { color: #28a745; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>✅ Demo Session Created Successfully</h1>
                    </div>
                    <div class="content">
                        <p>Hello <span class="highlight">%s</span>,</p>
                        
                        <p class="success">Your demo session has been created successfully!</p>
                        
                        <div class="session-details">
                            <h3>📋 Session Details</h3>
                            <p><strong>Title:</strong> %s</p>
                            <p><strong>Technology:</strong> %s</p>
                            <p><strong>Date:</strong> %s</p>
                            <p><strong>Time:</strong> %s</p>
                            <p><strong>Location:</strong> %s</p>
                            <p><strong>Difficulty:</strong> %s</p>
                            <p><strong>Duration:</strong> %d minutes</p>
                            <p><strong>Max Attendees:</strong> %d</p>
                            <p><strong>Session ID:</strong> <code>%s</code></p>
                        </div>
                        
                        <div class="session-details">
                            <h3>📝 Description</h3>
                            <p>%s</p>
                        </div>
                        
                        <p>The session has been scheduled and notifications have been sent to all participants.</p>
                        
                        <p>Best regards,<br>
                        <strong>Demo Session Management System</strong></p>
                    </div>
                    <div class="footer">
                        <p>This is an automated notification. Please do not reply to this email.</p>
                    </div>
                </div>
            </body>
            </html>
            """,
            
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