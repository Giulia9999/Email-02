package com.example.email02.student;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class StudentService {
    private final List<Student> students;

    @Autowired
    private JavaMailSender emailSender;

    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1,"Giulia", "Contarino", "maxpower88999@gmail.com"));
        students.add(new Student(2,"John", "Doe", "john.doe@example.com"));
        students.add(new Student(3,"Jane", "Doe", "jane.doe@example.com"));
        students.add(new Student(4,"Bob", "Smith", "bob.smith@example.com"));
    }

    public void sendEmailToStudent(long contactId, String subject, String text) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId() == contactId).findFirst();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            String htmlContent=  "<h1>Hello " + student.getName() + " " + student.getSurname() + "</h1>"
                    + "<h2>You have a new message:</h2>"
                    + "<img src='https://assets.telegraphindia.com/telegraph/c7ce1dd7-761c-4c13-a000-deccf2ff69bd.jpg' alt='Example image'>"
                    + "<h3>" + text + "</h3>";
            MimeMessage message = emailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(student.getEmail());
                helper.setSubject(subject);
                helper.setText(htmlContent);
                helper.setReplyTo("test@gmail.com");
                helper.setFrom("maxpower88999@gmail.com");
                emailSender.send(helper.getMimeMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Student not found with contact ID " + contactId);
        }
    }
}
