package com.wendymeng.school.question;

import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.Subject;
import com.wendymeng.school.option.Option;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;

    @Column(columnDefinition = "ENUM('MATH', 'ENGLISH', 'SOCIALSTUDIES', 'SCIENCE')")
    @Enumerated(EnumType.STRING)
    private Subject type;

    private Boolean correct;
    private Integer gradeLevel;

    @Column(columnDefinition = "ENUM('EASY', 'MEDIUM', 'DIFFICULT')")
    @Enumerated(EnumType.STRING)
    private Level hardLevel;

    private String description;
    private Option answer;

    @OneToMany(mappedBy = "questionID")
    private List<Option> options;

    @ManyToMany(mappedBy = "questions")
    private List<Exam> exams;
}
