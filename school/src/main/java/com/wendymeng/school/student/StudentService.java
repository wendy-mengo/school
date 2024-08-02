package com.wendymeng.school.student;

import jakarta.transaction.Transactional;
import org.aspectj.util.IStructureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

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
        student.setPassword(getSHA256(student.getPassword()));
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
        if (Objects.equals(getSHA256(password), student.getPassword())) {
            return true;
        }
        return false;
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
