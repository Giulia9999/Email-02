package com.example.email02.notifications;

import com.example.email02.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/{contactId}")
    public void sendEmailToStudent(@PathVariable long contactId, @RequestBody NotificationDTO notificationDTO) {
        try {
            studentService.sendEmailToStudent(contactId, notificationDTO.getTitle(), notificationDTO.getText());
        }catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with contact ID " + contactId, e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending email", e);
        }
    }
}
