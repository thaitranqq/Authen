package com.admin.admin.service;


import com.admin.admin.model.MailOrderInfor;
import com.admin.admin.model.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    public void sendemail(MailOrderInfor mailOrderInfor) throws MessagingException {
        List<Users> users = new ArrayList<>();
        Context context = new Context();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        context.setVariable("username",mailOrderInfor.getName());
        context.setVariable("users",mailOrderInfor.getProductList());
        context.setVariable("totalAmount",mailOrderInfor.getTotal());
        context.setVariable("phone",mailOrderInfor.getPhone());
        context.setVariable("address",mailOrderInfor.getAddress());
        String emailContent = templateEngine.process("form", context);
        helper.setTo(mailOrderInfor.getEmail());
        helper.setSubject("Information Checkout Order");
        helper.setText(emailContent, true);
        mailSender.send(message);
    }
}
