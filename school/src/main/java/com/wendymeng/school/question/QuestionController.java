package com.wendymeng.school.question;

import com.wendymeng.school.choice.Choice;
import com.wendymeng.school.choice.ChoiceService;
import com.wendymeng.school.exam.Exam;
import com.wendymeng.school.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class QuestionController {

     @Autowired
     private QuestionService questionService;

     @Autowired
     private ChoiceService choiceService;

     @Autowired
     private ExamService examService;

     @RequestMapping("{examID}/question/{id}")
     private String questionPage(@PathVariable Long id, Model model, @PathVariable Long examID){
         model.addAttribute("exam", examService.get(examID));
         Question question = questionService.get(id);
         model.addAttribute("question", question);
         List<Choice> choices = choiceService.listSpecificOptions(id);
         questionService.initializeQuestion(choices, question);
         model.addAttribute("choices", choices);
         model.addAttribute("chosenChoice", new Choice());


         return "displayQuestion";
     }
      @RequestMapping(value = "{examID}/question/{questionID}", method = RequestMethod.POST)
    public String submitQuestion(@PathVariable Long questionID, @ModelAttribute("chosenChoice") Choice chosenChoice, Model model, @PathVariable Long examID){
        model.addAttribute("question", questionService.get(questionID));
        model.addAttribute("exam", examService.get(examID));
         if (questionService.questionAnswered(chosenChoice, questionID)){
            return "questionCorrect";
        }
        return "questionIncorrect";
    }


//     @RequestMapping("/question/{id}/option/{id}")
//     private String checkAnswer(@PathVariable Long optionID){
//        if()
//     }

}
