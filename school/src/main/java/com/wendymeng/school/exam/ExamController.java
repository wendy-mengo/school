package com.wendymeng.school.exam;

import com.wendymeng.school.question.Question;
import com.wendymeng.school.student.Student;
import com.wendymeng.school.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExamController {
    @Autowired
    private ExamService service;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/examSchedule/{studentID}")
    public String viewHomePage(Model model, @PathVariable Long studentID){
        Student student = studentService.get(studentID);
        model.addAttribute("student", student);
        List<Exam> listExams = service.listStudentExam(studentID);
        model.addAttribute("listExams", listExams);

        return "examTable";
    }

     @RequestMapping("/dropExam/{studentID}")
    public String dropExamPage(Model model, @PathVariable Long studentID){
        Student student = studentService.get(studentID);
        model.addAttribute("student", student);
        List<Exam> listExams = service.listStudentExam(studentID);
        model.addAttribute("listExams", listExams);
        return "chooseDropExam";
    }

    @RequestMapping("/chooseDropExam/{studentID}/{id}")
    public String chooseDropExamPage(Model model, @PathVariable Long studentID, @PathVariable Long id){
        Student student = studentService.get(studentID);
        model.addAttribute("student", student);
        service.delete(id);
        return "ExamDropped";
    }

    @RequestMapping("/takeExam/{id}")
    public String takeExamPage(Model model, @PathVariable Long id){
        List<Question> listQuestions = service.initializeExam(id);
        model.addAttribute("listQuestions", listQuestions);
        return "questionsList";
    }

    @RequestMapping("/nextQuestion")
    public String nextQuestion(){
        return "";
    }

//    @RequestMapping("/addExam")
//    public String addExamPage(Model model){
//        Exam exam = new Exam();
//        model.addAttribute("exam", exam);
//        return "addExam";
//    }
//
//    @RequestMapping(value = "/adding", method = RequestMethod.POST)
//    public String addingPage(@ModelAttribute("exam") Exam exam){
//        service.save(exam);
//        return "examAdded";
//    }
}
