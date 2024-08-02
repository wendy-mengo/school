package com.wendymeng.school.admin;

import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.ExamRepository;
import com.wendymeng.school.student.Student;
import com.wendymeng.school.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;

    private static Logger LOGGER =  LogManager.getLogger();


    public List<Exam> listAllExams() {
        return examRepository.findAll();
    }

    public void save(Exam exam) {
        examRepository.save(exam);
    }

    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public void save(Student student) {
        student.setPassword(getSHA256(student.getPassword()));
        studentRepository.save(student);
    }

    public Admin get(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElse(null);
    }

    public boolean checkAdminPassword(Long id, String password) {
        if (!adminRepository.existsById(id)) {
            LOGGER.error("userID or password does not exist");

            return false;
        }
        Admin admin = get(id);
//        if (Objects.equals(getSHA256(password), admin.getPassword())) {
//            return true;
//        }
        if (Objects.equals(password, admin.getPassword())) {
            return true;
        }
        LOGGER.error("userID and password does not match");
        return false;
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(value -> studentRepository.delete(value));
    }

    public static String getSHA256(String data) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] byteData = md.digest();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
