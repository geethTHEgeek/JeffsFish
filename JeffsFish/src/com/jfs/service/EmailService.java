package com.jfs.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {


    /**
     * Sending email for Tax Invoice
     * @param messageBody
     * @param mailTo
     */
    public void sendEmail(final String messageBody, final String mailTo){

        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", "sadeshkasm@gmail.com");
        properties.put("mail.smtp.password", "2016c2s1");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("sadeshkasm@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject("Tax Invoice");
            message.setContent(messageBody,"text/html; charset=utf-8");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, "sadeshkasm@gmail.com", "2016c2s1");

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sending mail for Email inquiry
     * @param from
     * @param password
     * @param messageBody
     * @param mailTo
     */
    public void sendEmail2(final String from,final String password,final String messageBody, final String mailTo){

        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user",from );
        properties.put("mail.smtp.password",password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("sadeshkasm@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject("Tax Invoice");
            message.setContent(messageBody,"text/html; charset=utf-8");
            Transport transport = session.getTransport("smtp");
            transport.connect(host,from, password);

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
