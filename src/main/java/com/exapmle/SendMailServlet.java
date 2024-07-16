package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@WebServlet("/sendMail")
public class SendMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Send email
        String host = "smtp.gmail.com";
        final String username = "replace with your email"; 
        final String password = "replace with your email password"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(email));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("your email address"));
            mimeMessage.setSubject("New message from " + name);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h1>Thank you for your feedback!</h1>");

        }  catch (MessagingException e) {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>Error sending email</h1>");
    out.println("<p>Username: " + username + "</p>");
    out.println("<p>Password: " + password + "</p>");
    out.println("<p>Error Message: " + e.getMessage() + "</p>");
    throw new RuntimeException(e);
}
    }
}
