package com.wendymeng.school.student;

import jakarta.transaction.Transactional;
import org.aspectj.util.IStructureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).get();
    }

    public boolean checkPassword(Long id, String password) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        Student student = get(id);
        if (Objects.equals(password, student.getPassword())) {
            return true;
        }
        return false;
    }
}
