package com.wendymeng.school.admin;

import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.ExamService;
import com.wendymeng.school.student.Student;
import com.wendymeng.school.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @RequestMapping("/adminLogin")
    public String viewLoginPage(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "adminLogin";
    }

    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public String loginPage(@ModelAttribute("admin") Admin admin) {
        if (adminService.checkAdminPassword(admin.getAdminID(), admin.getPassword())) {
            return "adminActions";
        }
        return "adminLogin";
    }

    @RequestMapping("/addStudent")
    public String addStudentPage(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "addStudent";
    }

    @RequestMapping(value = "/addingStudent", method = RequestMethod.POST)
    public String addingStudentPage(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "studentAdded";
    }

    @RequestMapping("/addExam")
    public String addExamPage(Model model){
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        return "addExam";
    }

    @RequestMapping(value = "/adding", method = RequestMethod.POST)
    public String addingPage(@ModelAttribute("exam") Exam exam){
        examService.save(exam);
        return "examAdded";
    }

    @RequestMapping("/adminActions")
    public String actionPage(){
        return "adminActions";
    }

    @RequestMapping("/dropExam")
    public String dropExamPage(Model model){
        List<Student> listStudents = adminService.listAllStudents();
        model.addAttribute("listStudents", listStudents);
        return "adminDropExam";
    }

    @RequestMapping("/removeStudent")
    public String dropStudentPage(Model model){
        List<Student> listStudents = adminService.listAllStudents();
        model.addAttribute("listStudents", listStudents);
        return "chooseStudentDrop";
    }

    @RequestMapping("/studentDropped/{id}")
    public String studentDroppedPage(@PathVariable Long id){
        adminService.deleteStudent(id);
        return "studentDropped";
    }


}