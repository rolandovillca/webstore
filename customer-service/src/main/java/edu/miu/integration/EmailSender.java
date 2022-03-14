package edu.miu.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    public void sendEmail (String emailAddress, String emailMessage){
        System.out.println("Email message sent: '" + emailMessage + "' to: " + emailAddress);
    }
}
