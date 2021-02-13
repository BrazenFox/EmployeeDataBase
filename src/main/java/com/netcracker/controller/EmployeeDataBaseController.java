package com.netcracker.controller;

import com.netcracker.database.DataBase;
import com.netcracker.model.Email;
import com.netcracker.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;


import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.*;

@Controller
public class EmployeeDataBaseController {
    DataBase dataBase = new DataBase();

    @GetMapping("/")
    public String startPageGet(Model model) {
        return "startpage";
    }


    @GetMapping("/addemployee")
    public String addEmployeeGet(
            @RequestParam(name = "firstname", required = false, defaultValue = "") String firstName,
            @RequestParam(name = "middlename", required = false, defaultValue = "") String middleName,
            @RequestParam(name = "lastname", required = false, defaultValue = "") String lastName,
            @RequestParam(name = "age", required = false, defaultValue = "0") int age,
            @RequestParam(name = "salary", required = false, defaultValue = "0") int salary,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "telephone", required = false, defaultValue = "") String telephone,
            @RequestParam(name = "department", required = false, defaultValue = "") String department,
            Model model) {

        model.addAttribute("employee", new Employee(firstName, middleName, lastName, age, salary, email, telephone, department));
        return "addemployee";
    }


    @GetMapping("/searchemployee")
    public String searchEmployeeGet(Model model) {
        model.addAttribute("employee", new Employee());
        return "searchemployee";
    }


    @PostMapping("/searchemployee")
    public String searchEmployeePost(@ModelAttribute Employee employee, Model model, @RequestHeader(value = "User-Agent") String userAgent) throws IOException {
        Employee employee1 = dataBase.searchEmployee(employee);
        if (employee1 == null) {
            return "error-search";
        }

        employee1.setUserAgent(userAgent);
        Date time = new Date();
        employee1.setTime(time);
        model.addAttribute(employee1);
        return "employee";
    }


    @PostMapping("/addemployee")
    public String addEmployeePost(@ModelAttribute Employee employee) {
        dataBase.addEmployee(employee);
        return "employee";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (file.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        System.out.println(textBuilder.toString());
        String[] param = textBuilder.toString().split("\\|");
        if (param.length == 8) {
            return "redirect:/addemployee?" +
                    "firstname=" + param[0] +
                    "&middlename=" + param[1] +
                    "&lastname=" + param[2] +
                    "&age=" + param[3] +
                    "&salary=" + param[4] +
                    "&email=" + param[5] +
                    "&telephone=" + param[6] +
                    "&department=" + param[7];
        } else return "errorupload";

    }


    @GetMapping("/email")
    public String emailGet(Model model, @ModelAttribute Email email, @RequestParam(value = "email", required = false) String emailAddress) {
        model.addAttribute("email", new Email(emailAddress));
        return "email";
    }

    @PostMapping("/email")
    public String emailPost(@ModelAttribute Email email) throws MessagingException {

        final String username = "employeedatabase@rambler.ru";
        final String password = "QWERtyui1234";


        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.rambler.ru");
        prop.put("mail.smtp.port", "587"); //
        prop.put("mail.smtp.ssl.trust", "smtp.rambler.ru");
        prop.put("mail.smtp.user", username);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("employeedatabase@rambler.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setSubject(email.getTitle());
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(email.getContent(), "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
        return "redirect:/";
    }


}

