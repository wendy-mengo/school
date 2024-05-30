package com.wendymeng.school.student;

import com.wendymeng.school.exam.Exam;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;
    @Email
    private String emailAddress;
   // @NotEmpty
    private String password;
    private LocalDateTime lastExam;
    @OneToMany(mappedBy = "studentID")
    private List<Exam> listExams;
}
