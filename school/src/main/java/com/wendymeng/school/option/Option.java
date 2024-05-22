package com.wendymeng.school.option;

import com.wendymeng.school.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionID;
    @ManyToOne(fetch=FetchType.LAZY)
    private Question question;
    private String option;
}
