package com.wendymeng.school.exam;

import com.wendymeng.school.question.Question;
import com.wendymeng.school.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Exam {
    private float score;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examid;
    @Column(columnDefinition = "ENUM('MATH', 'ENGLISH', 'SOCIALSTUDIES', 'SCIENCE')")
    @Enumerated(EnumType.STRING)
    private Subject subject;
    private LocalDateTime dueDate;
    @OneToMany(mappedBy = "examID")
    private List<Question> questions;
    //@ManyToOne(fetch=FetchType.LAZY)
    private Long studentID;

}
