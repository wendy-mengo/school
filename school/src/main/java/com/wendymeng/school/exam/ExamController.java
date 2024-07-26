package com.wendymeng.school.exam;

import com.wendymeng.school.choice.Choice;
import com.wendymeng.school.choice.ChoiceService;
import com.wendymeng.school.question.Question;
import com.wendymeng.school.question.QuestionService;
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

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/examSchedule/{studentID}")
    public String viewHomePage(Model model, @PathVariable Long studentID) {
        Student student = studentService.get(studentID);
        for (Exam exam : student.getListExams()) {
            service.calcScore(exam.getExamid());
        }
        model.addAttribute("student", student);
        List<Exam> listExams = service.listStudentExam(studentID);
        model.addAttribute("listExams", listExams);

        return "examTable";
    }

    @RequestMapping("/dropExam/{studentID}")
    public String dropExamPage(Model model, @PathVariable Long studentID) {
        Student student = studentService.get(studentID);
        model.addAttribute("student", student);
        List<Exam> listExams = service.listStudentExam(studentID);
        model.addAttribute("listExams", listExams);
        return "chooseDropExam";
    }

    @RequestMapping("/chooseDropExam/{studentID}/{id}")
    public String chooseDropExamPage(Model model, @PathVariable Long studentID, @PathVariable Long id) {
        Student student = studentService.get(studentID);
        model.addAttribute("student", student);
        service.delete(id);
        return "ExamDropped";
    }

    @RequestMapping("/takeExam/{id}")
    public String takeExamPage(Model model, @PathVariable Long id) {
        model.addAttribute("exam", service.get(id));
        List<Question> listQuestions = service.initializeExam(id);
        model.addAttribute("listQuestions", listQuestions);
        return "questionsList";
    }

    @RequestMapping("{examID}/nextQuestion/")
    public String nextQuestion(@PathVariable Long examID, Model model) {
        model.addAttribute("exam", service.get(examID));
        Question question = service.findNextQuestion(examID);
        if (question == null) {
            return "testDone";
        }
        model.addAttribute("question", question);
        List<Choice> choices = choiceService.listSpecificOptions(question.getQuestionID());
        questionService.initializeQuestion(choices, question);
        model.addAttribute("choices", choices);
        model.addAttribute("chosenChoice", new Choice());
        return "displayQuestion";
    }

    @RequestMapping("/wrongQuestionList/{examID}")
    public String wrongQuestions(@PathVariable Long examID, Model model) {
        List<Question> wrongQuestions = service.wrongQuestionsFinder(examID);
        model.addAttribute("listQuestions", wrongQuestions);
        model.addAttribute("exam", service.get(examID));
        if (wrongQuestions == null) {
            return "backExamSched";
        }
        return "wrongQuestionsList";
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
