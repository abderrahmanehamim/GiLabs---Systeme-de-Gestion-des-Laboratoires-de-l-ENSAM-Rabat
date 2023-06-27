package com.Glab.LaboIntelligent.services.impl;




//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	//@Autowired
  //  private JavaMailSender emailSender;

    public void sendSimpleMessage(
      String[] to, String subject, String text) {
  
      //  SimpleMailMessage message = new SimpleMailMessage(); 
        //message.setFrom("  ");
        //message.setTo(to); 
        //message.setSubject(subject); 
        //message.setText(text);
        
        //emailSender.send(message);
        System.out.println("message transfert avec succes");
}
}
