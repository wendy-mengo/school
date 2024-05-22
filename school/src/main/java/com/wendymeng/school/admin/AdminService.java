package com.wendymeng.school.admin;

import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.ExamRepository;
import com.wendymeng.school.student.Student;
import com.wendymeng.school.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Exam> listAllExams(){
        return examRepository.findAll();
    }

    public void save(Exam exam){
        examRepository.save(exam);
    }

    public List<Student> listAllStudents(){
        return studentRepository.findAll();
    }

    public void save(Student student){
        studentRepository.save(student);
    }

    public Admin get(Long id){
        return adminRepository.findById(id).get();
    }

    public boolean checkAdminPassword(Long id, String password){
        if(!adminRepository.existsById(id)){
            return false;
        }
        Admin admin = get(id);
        if (Objects.equals(password, admin.getPassword())){
            return true;
        }
        return false;
    }

    public void deleteStudent(Long id){
        studentRepository.delete(studentRepository.findById(id).get());
    }
}
