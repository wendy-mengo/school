package com.wendymeng.school.choice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "choice")
@Getter
@Setter
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceID;
    //@ManyToOne(fetch=FetchType.LAZY)
    private Long questionID;
    private String description;
}
