package com.wendymeng.school.exam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByStudentID(Long studentID);
}

