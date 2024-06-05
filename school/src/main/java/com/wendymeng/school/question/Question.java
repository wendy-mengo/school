package com.wendymeng.school.question;

import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.Subject;
import com.wendymeng.school.choice.Choice;
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

    @Column(columnDefinition = "ENUM('EASY', 'MEDIUM', 'DIFFICULT')")
    @Enumerated(EnumType.STRING)
    private Level hardLevel;

    private String description;

    private Long answerID;

    @OneToMany(mappedBy = "questionID")
    private List<Choice> choices;

    @ManyToMany(mappedBy = "questions")
    private List<Exam> exams;

    private Integer count;
}
