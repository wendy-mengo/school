package com.wendymeng.school.choice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByQuestionID(Long questionID);
}
