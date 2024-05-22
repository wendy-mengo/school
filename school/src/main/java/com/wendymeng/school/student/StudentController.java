package com.wendymeng.school.student;

import com.wendymeng.school.exam.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/")
    public String viewLoginPage(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "login";
    }

    @RequestMapping(value = "/loginStudent", method = RequestMethod.POST)
    public String loginPage(@ModelAttribute("student") Student student){
        if (studentService.checkPassword(student.getStudentID(), student.getPassword())){
            return "index";
        }
        return "login";
    }

//    @RequestMapping("/addStudent")
//    public String addStudentPage(Model model){
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "addStudent";
//    }
//
//    @RequestMapping(value = "/addingStudent", method = RequestMethod.POST)
//    public String addingPage(@ModelAttribute("student") Student student){
//        studentService.save(student);
//        return "studentAdded";
//    }
}
